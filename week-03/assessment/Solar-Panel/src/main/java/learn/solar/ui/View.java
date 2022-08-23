package learn.solar.ui;

import learn.solar.models.Material;
import learn.solar.models.Panel;

import java.util.List;
public class View {

    Console console = new Console();

    public MainMenuChoice displayMenu() throws InvalidMenuChoiceException{
        System.out.println("\nMain Menu");
        System.out.println("=".repeat("Main Menu".length()));


        System.out.println("0. Exit");
        System.out.println("1. Add a Panel");
        System.out.println("2. Find Panels by Section");
        System.out.println("3. Update a panel");
        System.out.println("4. Delete a panel");

        int userChoice = console.getInt("Select [0-4]", 0,4);

        switch (userChoice){
            case 0:
                return MainMenuChoice.QUIT;
            case 1:
                return MainMenuChoice.CREATE_PANEL;
            case 2:
                return MainMenuChoice.DISPLAY_PANEL_BY_SECTION;
            case 3:
                return MainMenuChoice.EDIT_PANEL;
            case 4:
                return MainMenuChoice.DELETE_PANEL;
            default:
                throw new InvalidMenuChoiceException("Invalid input");
        }
    }

    public Panel getNewPanelDetails() {
        /*
            String section;
            private int row;
            private int column;
            private Material panelMaterial;
            private int yearInstalled;
            private boolean isTracking;
         */
        Panel toBuild = new Panel();

        String section = console.getRequiredString("Section: ");
        int row = console.getInt("Row: ",1, 250);
        int column = console.getInt("Column: ", 1 ,250);
        Material material = console.getMaterialType();
        int year = console.getInt("Installation Year: ", 1884,2021);
        boolean tracking = console.getBoolean("Tracked [y/n]: ");

        toBuild.setSection(section);
        toBuild.setRow(row);
        toBuild.setColumn(column);
        toBuild.setPanelMaterial(material);
        toBuild.setYearInstalled(year);
        toBuild.setTracking(tracking);

        return toBuild;
    }

    public void displayPanel(Panel fullyHydrated) {
        System.out.println("[Success]");
        System.out.println("Panel " + fullyHydrated.getSection() + "-" + fullyHydrated.getRow() + "-" + fullyHydrated.getColumn() + " added.");
    }

    public void displayError(String message) {
    }


    public void printHeader (String message){
        System.out.println();
        System.out.println(message);
        System.out.println("=".repeat(message.length()));
    }

    public String readPanelSectionType() {

        String section = console.getRequiredString("Section: ");

        return section;
    }
    
    public void displayPanelsBySection(List<Panel> panels){
        printHeader("Panels: ");
        if(panels.size() == 0){
            System.out.println("No Panels found. ");
        } else{
            System.out.printf("%s %s %s %s %s %n", "Row", "Col","Year","Material","Tracking");
            for(Panel p : panels){
                System.out.printf("%s %s %s %s %s%n",
                                p.getRow(),
                                p.getColumn(),
                                p.getYearInstalled(),
                                p.getPanelMaterial(),
                                p.isTracking());
            }
        }
    }

    public Panel editPanel( Panel toEdit ){//take existing panel object
        /*
        section, row, column, panelMaterial, yearInstalled, isTracking;
         */

        System.out.println("\nUpdating Panel: "+ toEdit.getSection() + "-" + toEdit.getRow() + "-" + toEdit.getColumn() + '\n');

        String updatedSection = console.editRequiredString(
                "Enter a new section ["
                        +toEdit.getSection()
                        +"] (press enter to skip): ", toEdit.getSection());


        int updatedRow = Integer.parseInt(console.editRequiredString(
                "Enter a new Row ["
                        +toEdit.getRow()
                        + "] (press enter to skip): ", String.valueOf(toEdit.getRow())));

        int updatedColumn = Integer.parseInt(console.editRequiredString(
                "Enter a new Column ["
                        +toEdit.getColumn()
                        + "] (press enter to skip): ", String.valueOf(toEdit.getColumn())));

        Material updatedMaterial = Material.valueOf(console.editRequiredString(
                "Enter a new Material ["
                        +toEdit.getPanelMaterial()
                        +"] (press enter to skip): ", String.valueOf(toEdit.getPanelMaterial())));

        int updatedYear = Integer.parseInt(console.editRequiredString(
                "Enter a new Installation Year ["
                        +toEdit.getYearInstalled()
                        + "] (press enter to skip): ", String.valueOf(toEdit.getYearInstalled())));

        boolean updatedTracking = Boolean.parseBoolean(console.editRequiredString(
                "Enter a new tracking status ['true' = yes, 'false' = no]:  ["
                        +toEdit.isTracking()
                        +"] (press enter to skip): ", String.valueOf(toEdit.isTracking())));


        toEdit.setSection(updatedSection);
        toEdit.setRow(updatedRow);
        toEdit.setColumn(updatedColumn);
        toEdit.setPanelMaterial(updatedMaterial);
        toEdit.setYearInstalled(updatedYear);
        toEdit.setTracking(updatedTracking);

        return toEdit; //Output the updated panel

    }

    public Panel getPanel() {
        Panel toBuild = new Panel();

        String section = console.getRequiredString("Section: ");
        int row = console.getInt("Row: ",1, 250);
        int column = console.getInt("Column: ", 1 ,250);

        toBuild.setSection(section);
        toBuild.setRow(row);
        toBuild.setColumn(column);

        return toBuild;
    }

}
