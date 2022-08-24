package learn.solar.models;

public enum Material {

    MULTICRYSTALLINE_SILICON("multicrystalline silicon"),
    MONOCRYSTALLINE_SILICON("monocrystalline silicon"),
    AMORPHOUS_SILICON("amorphous silicon"),
    CADMIUM_TELLURIDE("cadmium telluride"),
    COPPER_INDIUM_GALLIUM_SELENIDE("copper indium gallium selenide");

    String displayVal;

    Material( String displayVal ){
        this.displayVal = displayVal;
    }

    public String getDisplayVal(){
        return displayVal;
    }

}
