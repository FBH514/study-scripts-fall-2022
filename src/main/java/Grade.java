public class Grade {

    private static int grade = 0;
    private static int total = 0;
    private Records records = new Records();

    public double finalGrade(){
        if(!isValid(getGrade())){
            throw new IllegalArgumentException("Invalid grade");
        }
        setGrade(getGrade());
        return arithmetics(getGrade());
    }

    public void printGrade(){
        double percentage = arithmetics(getGrade());
        System.out.print("Scored " + getGrade() + "/" + getTotal());
        System.out.println("\t–\tYour grade is: " + String.format("%.2f", percentage) + "%");
        System.out.println();
    }

    public void reset(){
        total = 0;
        grade = 0;
    }


    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        Grade.grade = grade;
    }

    public int getTotal() {
        return total;
    }

    public boolean isValid(int grade){
        return grade >= 0 && grade <= 100;
    }

    public void message(boolean isCorrect){
        total++;
        if(isCorrect){
            System.out.println("Correct answer");
            return;
        }
        System.out.print("Incorrect answer");
        System.out.println();
    }

    public boolean leniency(String answer, Input input){
        int total = 0;
        for(int count = 0; count < input.getInput().length(); count++){
            try {
                char a = input.getInput().charAt(count);
                char b = answer.charAt(count);
                a = Character.toLowerCase(a);
                b = Character.toLowerCase(b);
                if (a == b || a + 1 == b || a - 1 == b) {
                    total++;
                }
            }
            catch (StringIndexOutOfBoundsException ignored){
            }
        }
        total = total * 100 / input.getInput().length();
        return total >= 80;
    }

    public boolean manualCorrection(String answer){
        System.out.println("The correct answer is: " + answer);
        Input input = new Input("Is manual correction justified in this case? (y/n)");
        while(true){
            if(input.getInput().equals("y")) {
                return true;
            }
            else if(input.getInput().equals("n")) {
                return false;
            }
            else {
                System.out.println("Invalid Input");
            }
            System.out.println();
        }
    }

    public void success(){
        message(true);
        records.addSuccess(true);
        setGrade(getGrade() + 1);
    }

    public void failure(String answer, boolean message){
        message(false);
        records.addSuccess(false);
        if(message) System.out.println(" -> Correct answer is: " + answer);
    }

    public double arithmetics(int grade){
        return (double) grade / total * 100;
    }
}

