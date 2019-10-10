void printPrimes (int max){
    for (int i = 2; i <= max; i++){
        Boolean prime = true ;
        double bound = Math.sqrt(i);
        for (int k = 2; k <= bound; k++){
            if (i % k == 0) {
                prime = false;
                break;
            }
        }
        if (prime) {
            System.out.println(i);
        }

    }
}
