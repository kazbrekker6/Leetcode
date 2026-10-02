class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> answer = new ArrayList<>();

        generate("", n, answer);

        return answer;
    }

    void generate(String current, int n, List<String> answer){
        if(current.length() == 2*n){
            if(isValid(current)){
                answer.add(current);
            }
            return;
        }
        generate(current + "(" , n, answer);

        generate(current + ")", n, answer);
    }

    boolean isValid(String current){
        int balanced = 0;

        for(char ch : current.toCharArray()){
            if(ch == '('){
                balanced++;
            }
            else{
                balanced--;
            }
            if(balanced < 0){
                return false;
            }
        }
        return balanced == 0;
    }
}