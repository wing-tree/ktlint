package yourpkgname.rules

import com.pinterest.ktlint.rule.engine.core.api.ElementType.RBRACE
import com.pinterest.ktlint.rule.engine.core.api.RuleId
import com.pinterest.ktlint.rule.engine.core.api.nextLeaf
import org.jetbrains.kotlin.com.intellij.lang.ASTNode
import org.jetbrains.kotlin.com.intellij.psi.PsiWhiteSpace
import org.jetbrains.kotlin.com.intellij.psi.impl.source.tree.LeafPsiElement
import yourpkgname.StandardRule

public class NoBlankLineBeforeRbraceRule : StandardRule("no-blank-line-before-rbrace") {
    override fun beforeVisitChildNodes(
        node: ASTNode,
        autoCorrect: Boolean,
        emit: (offset: Int, errorMessage: String, canBeAutoCorrected: Boolean) -> Unit,
    ) {
        if (node is PsiWhiteSpace &&
            node.textContains('\n') &&
            node.nextLeaf()?.elementType == RBRACE
        ) {
            val split = node.getText().split("\n")
            if (split.size > 2) {
                emit(
                    node.startOffset + split[0].length + split[1].length + 1,
                    "Unexpected blank line(s) before \"}\"",
                    true,
                )
                if (autoCorrect) {
                    (node as LeafPsiElement).rawReplaceWithText("${split.first()}\n${split.last()}")
                }
            }
        }
    }
}

public val NO_BLANK_LINE_BEFORE_RBRACE_RULE_ID: RuleId = NoBlankLineBeforeRbraceRule().ruleId
