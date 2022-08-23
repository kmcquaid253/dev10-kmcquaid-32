package learn.solar.data;

import learn.solar.models.Material;
import learn.solar.models.Panel;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class PanelFileRepository implements PanelRepository{

    //Don't want to hardcode filepath/value, want to accept it

    static final String DELIMITER = "~";
    private final String filePath;

    public PanelFileRepository(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<Panel> findAll() {
        List<Panel> toReturn = new ArrayList<>();

        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){

            //we'll assume for now there's a header row to skip
            reader.readLine();

            for( String line = reader.readLine();
                 line != null;
                 line = reader.readLine()){

                Panel converted = convertLineToPanel( line );
                toReturn.add( converted );
            }

        } catch( FileNotFoundException ex ){
            //returns the empty list
        } catch( IOException ex){
            ex.printStackTrace();
        }

        return toReturn;
    }

    @Override
    public Panel add(Panel toAdd) throws DataAccessException {

        List<Panel> currentPanels = findAll();//gets all existing panels

        int maxId = 0;
        for( Panel currentPanel : currentPanels ){
            if( currentPanel.getPanelId() > maxId ){//loop through to find the max id
                maxId = currentPanel.getPanelId();
            }
        }

        int newId = maxId + 1;//add one to the new id

        //set the id in toAdd
        toAdd.setPanelId( newId );

        currentPanels.add( toAdd );//add panel to list

        //  write the whole list
        writeAllPanels( currentPanels );

        //return the hydrated Customer object
        return toAdd;
    }

    //Needed to add Panel, writes the whole List
    private void writeAllPanels(List<Panel> toWrite) throws DataAccessException {
        try( PrintWriter writer = new PrintWriter(new FileWriter(filePath))){
            writer.println( "Panel ID"+DELIMITER+"Section"+DELIMITER+"Row"+DELIMITER+"Column"+DELIMITER+"Panel Materials"+DELIMITER+"Year Installed"+DELIMITER+"Is Tracking" );//hard code the header here
            for( Panel toCovert : toWrite ){
                String line = convertPanelToLine( toCovert );
                writer.println(line);
            }
        } catch (IOException ex){

            DataAccessException wrappedException = new DataAccessException("Could not write all panels.", ex);

            throw wrappedException;
        }
    }

    //Needed for 'writeAllPanels method'
    private String convertPanelToLine(Panel toCovert) {
        return toCovert.getPanelId() + DELIMITER + toCovert.getSection() + DELIMITER + toCovert.getRow() + DELIMITER + toCovert.getColumn() + DELIMITER + toCovert.getPanelMaterial() + DELIMITER + toCovert.getYearInstalled() + DELIMITER + toCovert.isTracking();
    }

    private Panel convertLineToPanel(String line) {
        String[] parts = line.split(DELIMITER);//consumes parts indefinitely

        Panel toBuild = new Panel();
        toBuild.setPanelId(Integer.parseInt(parts[0]));
        toBuild.setSection(parts[1]);
        toBuild.setRow(Integer.parseInt(parts[2]));
        toBuild.setColumn(Integer.parseInt(parts[3]));
        toBuild.setPanelMaterial(Material.valueOf(parts[4]));
        toBuild.setYearInstalled(Integer.parseInt(parts[5]));
        toBuild.setTracking(Boolean.parseBoolean(parts[6]));

        return toBuild;
    }

    @Override
    public boolean update(Panel editedPanel) throws DataAccessException { //Looking for an existing panel, if found, replace it and save it to the file
        List<Panel> all = findAll();
        for (int i = 0; i < all.size(); i++){
            if (all.get(i).getPanelId() == editedPanel.getPanelId()){
                all.set(i, editedPanel);
                writeAllPanels(all);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteBySectionRowColumn(String section, int row, int column) throws DataAccessException {
        List<Panel> all = findAll();
        for (int i = 0; i < all.size(); i++){
            if (all.get(i).getSection().equals(section) &&
                    all.get(i).getRow() == row &&
                    all.get(i).getColumn() == column){

                all.remove(i);
                writeAllPanels(all);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Panel> findPanelBySection(String section) throws DataAccessException{
        ArrayList<Panel> result = new ArrayList<>();
        for (Panel panel: findAll()){//look through every single panel
            if(panel.getSection().equals(section)){//if it matches...
                result.add(panel);//add it to the result
            }
        }
        return result;
    }

    @Override
    public Panel getPanelByLocation(String section, int row, int column) {

        for(Panel panel : findAll()){
            if(panel.getSection().equals(section) && panel.getRow() == row && panel.getColumn() == column){
                return panel;
            }
        }

        return null;
    }
}


