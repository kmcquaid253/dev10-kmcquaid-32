package learn.solar.ui;

import learn.solar.data.DataAccessException;
import learn.solar.data.PanelFileRepository;
import learn.solar.domain.PanelResult;
import learn.solar.domain.PanelService;
import learn.solar.models.Material;
import learn.solar.models.Panel;
import learn.solar.ui.View;


import java.util.List;

public class Controller {

    //field variable to hold our view and domain layer
    View view;
    PanelService service;

    public Controller(View view, PanelService service ){
        this.view = view;
        this.service = service;
    }

    public void run(){

        boolean hasQuit = false;

        while( !hasQuit ){
            try{


            MainMenuChoice userChoice = view.displayMenu();

            switch( userChoice ) {
                /*
                    QUIT,
                    GET_PANEL_BY_ID,
                    CREATE_PANEL,
                    EDIT_PANEL,
                    DELETE_PANEL
                 */
                case QUIT:
                    hasQuit = true;
                    System.out.println("\nGoodBye");
                    System.out.println("========");
                    break;
                case CREATE_PANEL:
                    Panel partiallyHydrated = view.getNewPanelDetails();
                    PanelResult fullyHydrated = service.addPanel(partiallyHydrated);
                    view.displayPanel(fullyHydrated.getPayLoad());

                    break;
                case DISPLAY_PANEL_BY_SECTION:
                    String section = view.readPanelSectionType();
                    List fully = service.findPanelBySection(section);
                    view.displayPanelsBySection(fully);
                    break;

                case EDIT_PANEL:
                    Panel panel = view.getPanel();//Ask to get panel
                    PanelResult lookupResult = service.getPanelByLocation(panel);//Ask service layer to give me that panel

                    if(lookupResult.isSuccess()){
                        Panel toEdit = lookupResult.getPayLoad();//have a panel object that can be edited

                        Panel edited = view.editPanel(toEdit);
                        PanelResult editResult = service.updatePanel( edited );//save in the backend
                    } else{
                        view.displayError(lookupResult.getErrorMessages().toString());
                    }
                    break;
                case DELETE_PANEL:
                    Panel lookup = view.getPanel();//Get panel info from user

                    String lookupSection = lookup.getSection(); //section
                    int lookupRow = lookup.getRow();            //row
                    int lookupColumn = lookup.getColumn();      //column

                    PanelResult result = service.deleteBySectionRowColumn(lookupSection, lookupRow, lookupColumn); // setting result to my "delete method" in my PanelService

                    if(result.isSuccess()){
                        System.out.println("[Success]");
                    } else {
                        view.displayError(result.getErrorMessages().toString());
                    }
            }
            } catch(InvalidMenuChoiceException ex){
                //print the exception
                ex.printStackTrace();
            } catch (DataAccessException ex) {
                view.displayError( ex.getMessage() );
            }
        }
    }


}
