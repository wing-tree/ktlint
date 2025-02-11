package yourpkgname.rules

import com.pinterest.ktlint.rule.engine.core.api.ElementType.AT
import com.pinterest.ktlint.rule.engine.core.api.ElementType.CLASS_BODY
import com.pinterest.ktlint.rule.engine.core.api.ElementType.COLONCOLON
import com.pinterest.ktlint.rule.engine.core.api.ElementType.COMMA
import com.pinterest.ktlint.rule.engine.core.api.ElementType.DOT
import com.pinterest.ktlint.rule.engine.core.api.ElementType.EOL_COMMENT
import com.pinterest.ktlint.rule.engine.core.api.ElementType.EXCLEXCL
import com.pinterest.ktlint.rule.engine.core.api.ElementType.FUN
import com.pinterest.ktlint.rule.engine.core.api.ElementType.LAMBDA_EXPRESSION
import com.pinterest.ktlint.rule.engine.core.api.ElementType.LBRACE
import com.pinterest.ktlint.rule.engine.core.api.ElementType.LBRACKET
import com.pinterest.ktlint.rule.engine.core.api.ElementType.LPAR
import com.pinterest.ktlint.rule.engine.core.api.ElementType.RBRACE
import com.pinterest.ktlint.rule.engine.core.api.ElementType.RBRACKET
import com.pinterest.ktlint.rule.engine.core.api.ElementType.RPAR
import com.pinterest.ktlint.rule.engine.core.api.ElementType.SAFE_ACCESS
import com.pinterest.ktlint.rule.engine.core.api.ElementType.SEMICOLON
import com.pinterest.ktlint.rule.engine.core.api.RuleId
import com.pinterest.ktlint.rule.engine.core.api.isLeaf
import com.pinterest.ktlint.rule.engine.core.api.isPartOfString
import com.pinterest.ktlint.rule.engine.core.api.nextLeaf
import com.pinterest.ktlint.rule.engine.core.api.prevLeaf
import com.pinterest.ktlint.rule.engine.core.api.upsertWhitespaceAfterMe
import com.pinterest.ktlint.rule.engine.core.api.upsertWhitespaceBeforeMe
import org.jetbrains.kotlin.com.intellij.lang.ASTNode
import org.jetbrains.kotlin.com.intellij.psi.PsiComment
import org.jetbrains.kotlin.com.intellij.psi.PsiWhiteSpace
import org.jetbrains.kotlin.com.intellij.psi.impl.source.tree.LeafPsiElement
import org.jetbrains.kotlin.com.intellij.psi.impl.source.tree.TreeElement
import org.jetbrains.kotlin.lexer.KtTokens
import org.jetbrains.kotlin.psi.KtLambdaExpression
import yourpkgname.StandardRule

public class SpacingAroundCurlyRule : StandardRule("curly-spacing") {
    override fun beforeVisitChildNodes(
        node: ASTNode,
        autoCorrect: Boolean,
        emit: (offset: Int, errorMessage: String, canBeAutoCorrected: Boolean) -> Unit,
    ) {
        if (node.isLeaf() && !node.isPartOfString()) {
            val prevLeaf = node.prevLeaf()
            val nextLeaf = node.nextLeaf()
            val spacingBefore: Boolean
            val spacingAfter: Boolean
            if (node.elementType == LBRACE) {
                spacingBefore =
                    prevLeaf is PsiWhiteSpace ||
                    prevLeaf?.elementType == AT ||
                    (
                        prevLeaf?.elementType == LPAR &&
                            ((node as LeafPsiElement).parent is KtLambdaExpression || node.parent.parent is KtLambdaExpression)
                        )
                spacingAfter = nextLeaf is PsiWhiteSpace || nextLeaf?.elementType == RBRACE
                if (prevLeaf is PsiWhiteSpace &&
                    !prevLeaf.textContains('\n') &&
                    prevLeaf.prevLeaf()?.let {
                        it.elementType == LPAR || it.elementType == AT
                    } == true
                ) {
                    emit(node.startOffset, "Unexpected space before \"${node.text}\"", true)
                    if (autoCorrect) {
                        prevLeaf.node.treeParent.removeChild(prevLeaf.node)
                    }
                }
                if (prevLeaf is PsiWhiteSpace && prevLeaf.textContains('\n') &&
                    (
                        prevLeaf.prevLeaf()?.let { it.elementType == RPAR || KtTokens.KEYWORDS.contains(it.elementType) } == true ||
                            node.treeParent.elementType == CLASS_BODY ||
                            (prevLeaf.treeParent.elementType == FUN && prevLeaf.treeNext.elementType != LAMBDA_EXPRESSION)
                        ) // allow newline for lambda return type
                ) {
                    emit(node.startOffset, "Unexpected newline before \"${node.text}\"", true)
                    if (autoCorrect) {
                        val eolCommentExists =
                            prevLeaf
                                .prevLeaf()
                                ?.let { it is PsiComment && it.elementType == EOL_COMMENT }
                                ?: false
                        if (eolCommentExists) {
                            val commentLeaf = prevLeaf.prevLeaf()!!
                            if (commentLeaf.prevLeaf() is PsiWhiteSpace) {
                                (commentLeaf.prevLeaf() as LeafPsiElement).rawRemove()
                            }
                            (node.treeParent.treeParent as TreeElement).removeChild(commentLeaf)
                            (node.treeParent as TreeElement).addChild(commentLeaf, node.treeNext)
                            node.upsertWhitespaceAfterMe(" ")
                        }
                        (prevLeaf as LeafPsiElement).rawReplaceWithText(" ")
                    }
                }
            } else if (node.elementType == RBRACE) {
                spacingBefore = prevLeaf is PsiWhiteSpace || prevLeaf?.elementType == LBRACE
                spacingAfter = nextLeaf == null || nextLeaf is PsiWhiteSpace || shouldNotToBeSeparatedBySpace(nextLeaf)
                if (nextLeaf is PsiWhiteSpace && !nextLeaf.textContains('\n') &&
                    shouldNotToBeSeparatedBySpace(nextLeaf.nextLeaf())
                ) {
                    emit(node.startOffset, "Unexpected space after \"${node.text}\"", true)
                    if (autoCorrect) {
                        nextLeaf.node.treeParent.removeChild(nextLeaf.node)
                    }
                }
            } else {
                return
            }
            when {
                !spacingBefore && !spacingAfter -> {
                    emit(node.startOffset, "Missing spacing around \"${node.text}\"", true)
                    if (autoCorrect) {
                        node.upsertWhitespaceBeforeMe(" ")
                        node.upsertWhitespaceAfterMe(" ")
                    }
                }
                !spacingBefore -> {
                    emit(node.startOffset, "Missing spacing before \"${node.text}\"", true)
                    if (autoCorrect) {
                        node.upsertWhitespaceBeforeMe(" ")
                    }
                }
                !spacingAfter -> {
                    emit(node.startOffset + 1, "Missing spacing after \"${node.text}\"", true)
                    if (autoCorrect) {
                        node.upsertWhitespaceAfterMe(" ")
                    }
                }
            }
        }
    }

    private fun shouldNotToBeSeparatedBySpace(leaf: ASTNode?): Boolean {
        val nextElementType = leaf?.elementType
        return (
            nextElementType == DOT ||
                nextElementType == COMMA ||
                nextElementType == RBRACKET ||
                nextElementType == RPAR ||
                nextElementType == SEMICOLON ||
                nextElementType == SAFE_ACCESS ||
                nextElementType == EXCLEXCL ||
                nextElementType == LBRACKET ||
                nextElementType == LPAR ||
                nextElementType == COLONCOLON
            )
    }
}

public val SPACING_AROUND_CURLY_RULE_ID: RuleId = SpacingAroundCurlyRule().ruleId
