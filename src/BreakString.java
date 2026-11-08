public class BreakString {
    public String getIngredientName() {
        return ingredientName;
    }

    public String getIngredientNameValue() {
        return ingredientNameValue;
    }

    public String getIngredientPrice() {
        return ingredientPrice;
    }

    public double getIngredientPriceValue() {
        return ingredientPriceValue;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getRequestType() {
        return requestType;
    }


    private String ingredientName;
    private String ingredientNameValue;
    private String ingredientPrice;
    private double ingredientPriceValue;
    private int status;
    private String message="";
    private String requestType;

    public BreakString(String body, String headerRequest){
        this.requestType=headerRequest;
        this.status=200;
        String nameHalf;
        String priceHalf;
        String[] breakBody= body.split("&",-1);

        if(breakBody.length!=2){
            this.message=" Invalid request Body ";
            this.status=400;

        }
        else {
            nameHalf=breakBody[0];
            priceHalf=breakBody[1];
            String[] nameHalfBreak=nameHalf.split("=",-1);
            String[] priceHalfBreak=priceHalf.split("=",-1);
            // check first half
            if(nameHalfBreak.length!=2){
                this.message=this.message+" Invalid ingredient Body ";
                this.status=400;

            }
            else {
                if(nameHalfBreak[0].isBlank() || nameHalfBreak[1].isBlank()){
                    this.message=this.message+" ingredient body has blank values ";
                    this.status=400;

                }
                else{
                    if(!nameHalfBreak[0].equals("ingredient")){
                        this.message=this.message+" ingredient body value is not correct ";
                        this.status=400;

                    }
                    else{

                        this.ingredientName=nameHalfBreak[0];
                        this.ingredientNameValue=nameHalfBreak[1];
                    }
                }
            }

            // second half

            if(priceHalfBreak.length!=2){
                this.message=this.message+" Invalid price Body ";
                this.status=400;

            }
            else {
                if(priceHalfBreak[0].isBlank() || priceHalfBreak[1].isBlank()){
                    this.message=this.message+" price body has blank values ";
                    this.status=400;

                }
                else{
                    if(!priceHalfBreak[0].equals("price")){
                        this.message=this.message+" price body value is not correct ";
                        this.status=400;

                    }
                    else{
                        try {
                            double checkPrice = Double.parseDouble(priceHalfBreak[1]);
                            if (checkPrice <= 0|| !Double.isFinite(checkPrice) )
                            {
                                this.message=this.message+" price cannot be negative or zero or infinite ";
                                this.status=400;


                            }
                            else{


                                this.ingredientPrice=priceHalfBreak[0];
                                this.ingredientPriceValue=checkPrice;
                            }
                        }
                        catch (NumberFormatException exception) {

                            this.message=this.message + " price must be number ";
                            this.status=400;

                        }
                    }
                }
            }
        }
        if (this.status == 200) {
            this.message = "Request is valid";
        }

        }

    }
