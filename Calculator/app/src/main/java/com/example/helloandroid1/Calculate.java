package com.example.helloandroid1;

public class Calculate {
    private float num1;
    private float num2;
    private float result=0;
    private String result2;

    public Calculate( ) {
    }

    public String operation(int op){
        if(op == R.id.add){
          setResult(getNum1()+getNum2());
          return String.valueOf(result);
        }else if (op == R.id.subtract){
            setResult(getNum1()-getNum2());
            return String.valueOf(result);
        }else if (op == R.id.divide){
            try{
                setResult(getNum1()/getNum2());
                return String.valueOf(result);
            } catch( Exception e){
                return "Can't Divide";
            }
        }else if (op == R.id.multiply){
            setResult(getNum1()*getNum2());
            return String.valueOf(result);
        }
        return String.valueOf(result);
    }

    public String Result(int op){
        if(op == R.id.add){
            result2= getNum1()+"+"+ getNum2();
            return result2;
        }else if (op == R.id.subtract){
            result2= getNum1()+"-"+ getNum2();
            return result2;
        }else if (op == R.id.divide){
            try{
                result2= getNum1()+"/"+ getNum2();
                return result2;
            } catch( Exception e){
                return "Can't Divide";
            }
        }else if (op == R.id.multiply){
            result2= getNum1()+"*"+ getNum2();
            return result2;
        }
        return result2;
    }


    public void setNum1( float num ) {
        if( num > 0 )
            num1 = num;
    }
    public void setNum2( float num ) {
        if( num > 0 )
            num2 = num;
    }
    public void setResult( float num ) {
            result = num;
    }
    public float getNum1( ) {
        return num1;
    }
    public float getNum2( ) {
        return num2;
    }


}
