package com.example.SynonymFinder;

class FindSynonym {
    /**
     * A string to find a synonym for.
     */
    private String word;

    /**
     * Initializes word to the user's input.
     * @param setWord - The user's input.
     */
    FindSynonym(String setWord) {
        word = setWord;
    }
    String getUrl() {
        String use = word.trim().toLowerCase();
        String startURL = "https://dictionaryapi.com/api/v3/references/thesaurus/json/";
        String key = "?key=48be692f-801c-4dd7-8f0b-f65e90717d89";
        return startURL + use + key;
    }
}
