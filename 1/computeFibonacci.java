int computeFibonacci (int n){
    int result = 1;
    int lastResult = 1;
    for (int i=0;i<n-2;i++){
        int temp = result;
        result = lastResult + result;
        lastResult = temp;
    }
    return result;
}
