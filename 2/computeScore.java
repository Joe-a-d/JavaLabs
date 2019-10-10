// ALG: parse word & toLowercase & check set membership .contains() &

Integer computeScore (String word){

    int score = 0;
    // map characters to points as disjoint sets
    Set<Character> chars1 = new HashSet<>(Arrays.asList('a','u','l','r','i','n','s','e','o','t'));
    Set<Character> chars2 = new HashSet<>(Arrays.asList('g','d'));
    Set<Character> chars3 = new HashSet<>(Arrays.asList('p','b','c','m'));
    Set<Character> chars4 = new HashSet<>(Arrays.asList('f','v','h','w','y'));
    Set<Character> chars5 = new HashSet<>(Arrays.asList('k'));
    Set<Character> chars8 = new HashSet<>(Arrays.asList('x','j'));
    Set<Character> chars10 = new HashSet<>(Arrays.asList('z','q'));

    // parse word
    // ASCII valid range [97-122]

    String lword = word.toLowerCase();
    char[] pString = lword.toCharArray();

    for (char c : pString){
        int asc = (int) c;
        if (asc < 97 || asc > 122){
            continue;
        }else if (chars1.contains(c)){
            score += 1;
        }else if (chars4.contains(c)){
            score += 4;
        }else if (chars3.contains(c)){
            score += 3;
        }else if (chars2.contains(c)){
            score += 2;
        }else if (chars5.contains(c)){
            score += 5;
        }else if (chars8.contains(c)){
            score += 8;
        }else {
            score += 10;
        }


    }
    return(score);

}


// Resources Consulted:
/// https://www.codejava.net/java-core/collections/java-set-collection-tutorial-and-examples
