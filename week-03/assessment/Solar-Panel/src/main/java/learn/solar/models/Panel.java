package learn.solar.models;

public class Panel {
    /*
     **Section**: name that identifies where the panel is installed.
     **Row**: the row number in the section where the panel is installed.
     **Column**: the column number in the section where the panel is installed.
     **Year Installed**
     **Is Installed**: determines if the panel is installed with sun-tracking hardware, boolean

     */

    //fields
    private int panelId;
    private String section;
    private int row;
    private int column;
    private Material panelMaterial;
    private int yearInstalled;

    private boolean isTracking;

    public int getPanelId() {
        return panelId;
    }

    public void setPanelId(int panelId) {
        this.panelId = panelId;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public Material getPanelMaterial() {
        return panelMaterial;
    }

    public void setPanelMaterial(Material panelMaterial) {
        this.panelMaterial = panelMaterial;
    }

    public int getYearInstalled() {
        return yearInstalled;
    }

    public void setYearInstalled(int yearInstalled) {
        this.yearInstalled = yearInstalled;
    }

    public boolean isTracking() {
        return isTracking;
    }

    public void setTracking(boolean tracking) {
        isTracking = tracking;
    }
}