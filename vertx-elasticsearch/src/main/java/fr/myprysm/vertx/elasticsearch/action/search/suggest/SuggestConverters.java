package fr.myprysm.vertx.elasticsearch.action.search.suggest;

import fr.myprysm.vertx.elasticsearch.action.search.SearchConverters;
import fr.myprysm.vertx.elasticsearch.action.support.ScoreDoc;
import fr.myprysm.vertx.elasticsearch.converter.CommonConverters;
import fr.myprysm.vertx.elasticsearch.converter.ConverterUtils;
import io.vertx.core.json.JsonObject;
import org.elasticsearch.search.suggest.Suggest;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public interface SuggestConverters {

    static Suggestion fromJsonObject(JsonObject json) {
        Suggestion suggestion = null;
        if (json.getValue("type") instanceof String) {
            if (json.getValue("type") == CompletionSuggestion.SUGGESTION_NAME) {
                suggestion = new CompletionSuggestion(json);
            } else if (json.getValue("type") == TermSuggestion.SUGGESTION_NAME) {
                suggestion = new TermSuggestion(json);
            } else if (json.getValue("type") == PhraseSuggestion.SUGGESTION_NAME) {
                suggestion = new PhraseSuggestion(json);
            }
        }

        return suggestion;
    }

    static Map<String, Suggestion> suggestToDataObject(Suggest suggest) {
        Map<String, Suggestion> suggestions = new HashMap<>(suggest.size());

        suggest.forEach(suggestion -> {
            if (suggestion instanceof org.elasticsearch.search.suggest.completion.CompletionSuggestion) {
                suggestions.put(
                        suggestion.getName(),
                        completionSuggestionToDataObject((org.elasticsearch.search.suggest.completion.CompletionSuggestion) suggestion)
                );
            } else if (suggestion instanceof org.elasticsearch.search.suggest.term.TermSuggestion) {
                suggestions.put(
                        suggestion.getName(),
                        termSuggestionToDataObject((org.elasticsearch.search.suggest.term.TermSuggestion) suggestion)
                );
            } else if (suggestion instanceof org.elasticsearch.search.suggest.phrase.PhraseSuggestion) {
                suggestions.put(
                        suggestion.getName(),
                        phraseSuggestionToDataObject((org.elasticsearch.search.suggest.phrase.PhraseSuggestion) suggestion)
                );

            }
        });
        return suggestions;
    }

    static PhraseSuggestion phraseSuggestionToDataObject(org.elasticsearch.search.suggest.phrase.PhraseSuggestion suggestion) {
        return new PhraseSuggestion(
                suggestion.getName(),
                ConverterUtils.convert(suggestion.getEntries(), SuggestConverters::phraseEntryToDataObject)
        );
    }

    static PhraseEntry phraseEntryToDataObject(org.elasticsearch.search.suggest.phrase.PhraseSuggestion.Entry entry) {
        return fillGenericEntry(new PhraseEntry(ConverterUtils.convert(entry.getOptions(), SuggestConverters::phraseOptionToDataObject)), entry);
    }

    static PhraseOption phraseOptionToDataObject(Suggest.Suggestion.Entry.Option option) {
        return fillGenericOption(new PhraseOption(), option);
    }

    static TermSuggestion termSuggestionToDataObject(org.elasticsearch.search.suggest.term.TermSuggestion suggestion) {
        return new TermSuggestion(
                suggestion.getName(),
                ConverterUtils.convert(suggestion.getEntries(), SuggestConverters::termEntryToDataObject)
        );
    }

    static TermEntry termEntryToDataObject(org.elasticsearch.search.suggest.term.TermSuggestion.Entry entry) {
        return fillGenericEntry(new TermEntry(ConverterUtils.convert(entry.getOptions(), SuggestConverters::termOptionToDataObject)), entry);

    }

    static TermOption termOptionToDataObject(org.elasticsearch.search.suggest.term.TermSuggestion.Entry.Option esOption) {
        return fillGenericOption(new TermOption(esOption.getFreq()), esOption);
    }

    static CompletionSuggestion completionSuggestionToDataObject(org.elasticsearch.search.suggest.completion.CompletionSuggestion suggestion) {
        return new CompletionSuggestion(
                suggestion.getName(),
                ConverterUtils.convert(suggestion.getEntries(), SuggestConverters::completionEntryToDataObject)
        );
    }

    static CompletionEntry completionEntryToDataObject(org.elasticsearch.search.suggest.completion.CompletionSuggestion.Entry entry) {
        return fillGenericEntry(new CompletionEntry(ConverterUtils.convert(entry.getOptions(), SuggestConverters::completionOptionToDataObject)), entry);

    }

    static CompletionOption completionOptionToDataObject(org.elasticsearch.search.suggest.completion.CompletionSuggestion.Entry.Option esOption) {
        CompletionOption option = fillGenericOption(new CompletionOption(), esOption);
        if (esOption.getDoc() != null) {
            option.setDoc(scoreDocToDataObject(esOption.getDoc()));
        }

        if (esOption.getHit() != null) {
            option.setHit(SearchConverters.searchHitToDataObject(esOption.getHit()));
        }

        if (esOption.getContexts() != null && !esOption.getContexts().isEmpty()) {
            option.setContexts(ConverterUtils.convert(esOption.getContexts(), set -> {
                Set<String> result = new HashSet<>();
                for (CharSequence charSequence : set) {
                    String s = CommonConverters.charSequenceToString(charSequence);
                    result.add(s);
                }
                return result;
            }));
        }
        return option;
    }

    static ScoreDoc scoreDocToDataObject(org.apache.lucene.search.ScoreDoc doc) {
        return new ScoreDoc(doc.score, doc.doc, doc.shardIndex);
    }

    @SuppressWarnings("unchecked")
    static <T extends Entry, U extends Suggest.Suggestion.Entry> T fillGenericEntry(T entry, U esEntry) {
        return (T) entry.setLength(esEntry.getLength())
                .setOffset(esEntry.getOffset())
                .setText(esEntry.getText() == null ? null : esEntry.getText().string());
    }

    @SuppressWarnings("unchecked")
    static <T extends Option, U extends Suggest.Suggestion.Entry.Option> T fillGenericOption(T option, U esOption) {
        if (esOption.getHighlighted() != null) {
            option.setHighlighted(esOption.getHighlighted().string());
        }

        if (esOption.getText() != null) {
            option.setText(esOption.getText().string());
        }

        return (T) option
                .setCollateMatch(esOption.collateMatch())
                .setScore(esOption.getScore());
    }

}
