package yourpkgname.rules

import com.pinterest.ktlint.rule.engine.core.api.ElementType.DOT_QUALIFIED_EXPRESSION
import com.pinterest.ktlint.rule.engine.core.api.RuleId
import org.jetbrains.kotlin.com.intellij.lang.ASTNode
import org.jetbrains.kotlin.com.intellij.psi.PsiWhiteSpace
import org.jetbrains.kotlin.com.intellij.psi.impl.source.tree.LeafPsiElement
import yourpkgname.StandardRule

public class NoBlankLinesInChainedMethodCallsRule : StandardRule("no-blank-lines-in-chained-method-calls") {
    override fun beforeVisitChildNodes(
        node: ASTNode,
        autoCorrect: Boolean,
        emit: (offset: Int, errorMessage: String, canBeAutoCorrected: Boolean) -> Unit,
    ) {
        val isBlankLine = node is PsiWhiteSpace && node.getText().contains("\n\n")
        if (isBlankLine && node.treeParent.elementType == DOT_QUALIFIED_EXPRESSION) {
            emit(node.startOffset + 1, "Needless blank line(s)", true)

            if (autoCorrect) {
                (node as LeafPsiElement).rawReplaceWithText("\n" + node.getText().split("\n\n")[1])
            }
        }
    }
}

public val NO_BLANK_LINES_IN_CHAINED_METHOD_CALLS_RULE_ID: RuleId = NoBlankLinesInChainedMethodCallsRule().ruleId
