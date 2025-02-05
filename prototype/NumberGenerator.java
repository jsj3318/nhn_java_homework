@CreationType(type="SingleTon")
class NumberGenerator implements DataBean{

    @Override
    public NumberGenerator clone() {
        return new NumberGenerator();
    }

}