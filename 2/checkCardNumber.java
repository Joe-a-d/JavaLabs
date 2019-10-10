boolean checkCardNumber (String cardNumber){

// Checks if str is composed of number chars only
    try {
        Long iNum = new Long(cardNumber) ;

    } catch (Exception e){
        System.out.println(e);
        return false ;
    }

// Initial checks for basic requirements (size of input + inital chars)
    int numLen = cardNumber.length();

    char[] numArray = cardNumber.toCharArray();

    Set<Integer> lens = new HashSet<>(Arrays.asList(13,15,16,19));
    Set<Character> chars = new HashSet<>(Arrays.asList('4','3','5','2'));


    if (!(lens.contains(numLen))){
        System.out.println("Failed at len");
        return false ;
    } else if (!(chars.contains(numArray[0]))){
        System.out.println("Failed at member");
        return false;
    } else {
        ;
    }

// Modified Luhn's : Checks if size is even or odd , if e start at 0, o at 1
// the algorithm is worngly applied, you're suposed to sum all numbers, not just the even or odds
//comeback to it starting from fresh
int checkSum = 0;



}
