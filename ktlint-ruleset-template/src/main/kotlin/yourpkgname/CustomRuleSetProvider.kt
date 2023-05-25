package yourpkgname

import com.pinterest.ktlint.cli.ruleset.core.api.RuleSetProviderV3
import com.pinterest.ktlint.rule.engine.core.api.RuleProvider
import com.pinterest.ktlint.rule.engine.core.api.RuleSetId
import yourpkgname.rules.AnnotationRule
import yourpkgname.rules.AnnotationSpacingRule
import yourpkgname.rules.ArgumentListWrappingRule
import yourpkgname.rules.BlockCommentInitialStarAlignmentRule
import yourpkgname.rules.ChainWrappingRule
import yourpkgname.rules.ClassNamingRule
import yourpkgname.rules.CommentWrappingRule
import yourpkgname.rules.ContextReceiverWrappingRule
import yourpkgname.rules.DiscouragedCommentLocationRule
import yourpkgname.rules.EnumEntryNameCaseRule
import yourpkgname.rules.EnumWrappingRule
import yourpkgname.rules.FilenameRule
import yourpkgname.rules.FinalNewlineRule
import yourpkgname.rules.FunKeywordSpacingRule
import yourpkgname.rules.FunctionNamingRule
import yourpkgname.rules.FunctionReturnTypeSpacingRule
import yourpkgname.rules.FunctionSignatureRule
import yourpkgname.rules.FunctionStartOfBodySpacingRule
import yourpkgname.rules.FunctionTypeReferenceSpacingRule
import yourpkgname.rules.IfElseBracingRule
import yourpkgname.rules.IfElseWrappingRule
import yourpkgname.rules.IndentationRule
import yourpkgname.rules.KdocWrappingRule
import yourpkgname.rules.MaxLineLengthRule
import yourpkgname.rules.ModifierListSpacingRule
import yourpkgname.rules.ModifierOrderRule
import yourpkgname.rules.MultiLineIfElseRule
import yourpkgname.rules.MultilineExpressionWrapping
import yourpkgname.rules.NoBlankLineBeforeRbraceRule
import yourpkgname.rules.NoBlankLineInListRule
import yourpkgname.rules.NoBlankLinesInChainedMethodCallsRule
import yourpkgname.rules.NoConsecutiveBlankLinesRule
import yourpkgname.rules.NoConsecutiveCommentsRule
import yourpkgname.rules.NoEmptyClassBodyRule
import yourpkgname.rules.NoEmptyFirstLineInClassBodyRule
import yourpkgname.rules.NoEmptyFirstLineInMethodBlockRule
import yourpkgname.rules.NoLineBreakAfterElseRule
import yourpkgname.rules.NoLineBreakBeforeAssignmentRule
import yourpkgname.rules.NoMultipleSpacesRule
import yourpkgname.rules.NoSemicolonsRule
import yourpkgname.rules.NoSingleLineBlockCommentRule
import yourpkgname.rules.NoTrailingSpacesRule
import yourpkgname.rules.NoUnitReturnRule
import yourpkgname.rules.NoUnusedImportsRule
import yourpkgname.rules.NullableTypeSpacingRule
import yourpkgname.rules.ParameterListSpacingRule
import yourpkgname.rules.ParameterListWrappingRule
import yourpkgname.rules.ParameterWrappingRule
import yourpkgname.rules.PropertyNamingRule
import yourpkgname.rules.PropertyWrappingRule
import yourpkgname.rules.SpacingAroundAngleBracketsRule
import yourpkgname.rules.SpacingAroundColonRule
import yourpkgname.rules.SpacingAroundCommaRule
import yourpkgname.rules.SpacingAroundCurlyRule
import yourpkgname.rules.SpacingAroundDotRule
import yourpkgname.rules.SpacingAroundDoubleColonRule
import yourpkgname.rules.SpacingAroundKeywordRule
import yourpkgname.rules.SpacingAroundOperatorsRule
import yourpkgname.rules.SpacingAroundParensRule
import yourpkgname.rules.SpacingAroundRangeOperatorRule
import yourpkgname.rules.SpacingAroundUnaryOperatorRule
import yourpkgname.rules.SpacingBetweenDeclarationsWithAnnotationsRule
import yourpkgname.rules.SpacingBetweenDeclarationsWithCommentsRule
import yourpkgname.rules.SpacingBetweenFunctionNameAndOpeningParenthesisRule
import yourpkgname.rules.StringTemplateIndentRule
import yourpkgname.rules.StringTemplateRule
import yourpkgname.rules.TrailingCommaOnCallSiteRule
import yourpkgname.rules.TrailingCommaOnDeclarationSiteRule
import yourpkgname.rules.TryCatchFinallySpacingRule
import yourpkgname.rules.TypeArgumentListSpacingRule
import yourpkgname.rules.TypeParameterListSpacingRule
import yourpkgname.rules.WrappingRule

public class CustomRuleSetProvider :
    RuleSetProviderV3(RuleSetId("doowit-ruleset")) {
    override fun getRuleProviders(): Set<RuleProvider> =
        setOf(
            RuleProvider { AnnotationRule() },
            RuleProvider { AnnotationSpacingRule() },
            RuleProvider { ArgumentListWrappingRule() },
            RuleProvider { BlockCommentInitialStarAlignmentRule() },
            RuleProvider { ChainWrappingRule() },
            RuleProvider { ClassNamingRule() },
            RuleProvider { CommentWrappingRule() },
            RuleProvider { ContextReceiverWrappingRule() },
            RuleProvider { DiscouragedCommentLocationRule() },
            RuleProvider { EnumEntryNameCaseRule() },
            RuleProvider { EnumWrappingRule() },
            RuleProvider { FilenameRule() },
            RuleProvider { FinalNewlineRule() },
            RuleProvider { FunctionNamingRule() },
            RuleProvider { FunctionReturnTypeSpacingRule() },
            RuleProvider { FunctionSignatureRule() },
            RuleProvider { FunctionStartOfBodySpacingRule() },
            RuleProvider { FunctionTypeReferenceSpacingRule() },
            RuleProvider { FunKeywordSpacingRule() },
            RuleProvider { IfElseBracingRule() },
            RuleProvider { IfElseWrappingRule() },
            RuleProvider { IndentationRule() },
            RuleProvider { KdocWrappingRule() },
            RuleProvider { MaxLineLengthRule() },
            RuleProvider { ModifierListSpacingRule() },
            RuleProvider { ModifierOrderRule() },
            RuleProvider { MultiLineIfElseRule() },
            RuleProvider { MultilineExpressionWrapping() },
            RuleProvider { NoBlankLineBeforeRbraceRule() },
            RuleProvider { NoBlankLineInListRule() },
            RuleProvider { NoBlankLinesInChainedMethodCallsRule() },
            RuleProvider { NoConsecutiveBlankLinesRule() },
            RuleProvider { NoConsecutiveCommentsRule() },
            RuleProvider { NoEmptyClassBodyRule() },
            RuleProvider { NoEmptyFirstLineInClassBodyRule() },
            RuleProvider { NoEmptyFirstLineInMethodBlockRule() },
            RuleProvider { NoLineBreakAfterElseRule() },
            RuleProvider { NoLineBreakBeforeAssignmentRule() },
            RuleProvider { NoMultipleSpacesRule() },
            RuleProvider { NoSemicolonsRule() },
            RuleProvider { NoSingleLineBlockCommentRule() },
            RuleProvider { NoTrailingSpacesRule() },
            RuleProvider { NoUnitReturnRule() },
            RuleProvider { NoUnusedImportsRule() },
            RuleProvider { NullableTypeSpacingRule() },
            RuleProvider { ParameterListSpacingRule() },
            RuleProvider { ParameterListWrappingRule() },
            RuleProvider { ParameterWrappingRule() },
            RuleProvider { PropertyNamingRule() },
            RuleProvider { PropertyWrappingRule() },
            RuleProvider { SpacingAroundAngleBracketsRule() },
            RuleProvider { SpacingAroundColonRule() },
            RuleProvider { SpacingAroundCommaRule() },
            RuleProvider { SpacingAroundCurlyRule() },
            RuleProvider { SpacingAroundDotRule() },
            RuleProvider { SpacingAroundDoubleColonRule() },
            RuleProvider { SpacingAroundKeywordRule() },
            RuleProvider { SpacingAroundOperatorsRule() },
            RuleProvider { SpacingAroundParensRule() },
            RuleProvider { SpacingAroundRangeOperatorRule() },
            RuleProvider { SpacingAroundUnaryOperatorRule() },
            RuleProvider { SpacingBetweenDeclarationsWithAnnotationsRule() },
            RuleProvider { SpacingBetweenDeclarationsWithCommentsRule() },
            RuleProvider { SpacingBetweenFunctionNameAndOpeningParenthesisRule() },
            RuleProvider { StringTemplateRule() },
            RuleProvider { StringTemplateIndentRule() },
            RuleProvider { TrailingCommaOnDeclarationSiteRule() },
            RuleProvider { TryCatchFinallySpacingRule() },
            RuleProvider { TypeArgumentListSpacingRule() },
            RuleProvider { TypeParameterListSpacingRule() },
            RuleProvider { WrappingRule() },
        )
}
