package yourpkgname.rules

import com.pinterest.ktlint.rule.engine.core.api.ElementType.CLASS_BODY
import com.pinterest.ktlint.rule.engine.core.api.ElementType.LBRACE
import com.pinterest.ktlint.rule.engine.core.api.ElementType.RBRACE
import com.pinterest.ktlint.rule.engine.core.api.ElementType.WHITE_SPACE
import com.pinterest.ktlint.rule.engine.core.api.RuleId
import com.pinterest.ktlint.rule.engine.core.api.children
import com.pinterest.ktlint.rule.engine.core.api.isPartOf
import com.pinterest.ktlint.rule.engine.core.api.nextLeaf
import org.jetbrains.kotlin.com.intellij.lang.ASTNode
import org.jetbrains.kotlin.psi.KtObjectLiteralExpression
import yourpkgname.StandardRule

public class NoEmptyClassBodyRule : StandardRule("no-empty-class-body") {
    override fun beforeVisitChildNodes(
        node: ASTNode,
        autoCorrect: Boolean,
        emit: (offset: Int, errorMessage: String, canBeAutoCorrected: Boolean) -> Unit,
    ) {
        if (node.elementType == CLASS_BODY &&
            node.firstChildNode?.let { n ->
                n.elementType == LBRACE &&
                    n.nextLeaf { it.elementType != WHITE_SPACE }?.elementType == RBRACE
            } == true &&
            !node.isPartOf(KtObjectLiteralExpression::class) &&
            node.treeParent.firstChildNode.children().none { it.text == "companion" }
        ) {
            emit(node.startOffset, "Unnecessary block (\"{}\")", true)
            if (autoCorrect) {
                val prevNode = node.treePrev
                if (prevNode.elementType == WHITE_SPACE) {
                    // remove space between declaration and block
                    prevNode.treeParent.removeChild(prevNode)
                }
                // remove block
                node.treeParent.removeChild(node)
            }
        }
    }
}

public val NO_EMPTY_CLASS_BODY_RULE_ID: RuleId = NoEmptyClassBodyRule().ruleId
