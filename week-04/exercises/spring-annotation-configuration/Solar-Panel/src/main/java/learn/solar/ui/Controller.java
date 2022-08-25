package learn.solar.ui;

import learn.solar.data.DataAccessException;
import learn.solar.domain.PanelResult;
import learn.solar.domain.PanelService;
import learn.solar.models.Panel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.List;

@Component // <-@Component is similar to the XML <bean> element. It indicates that a class should be added to the DI container. By default, a @Component has singleton scope.
public class Controller {

    //field variable to hold our view and domain layer
    View view;

    @Autowired // direct field injection
    PanelService service;

//    @Autowired // setter injection
//    public void setView(View view) {
//        this.view = view;
//    }

    @Autowired // indicates both a view and service should be injected. ALSO, @Autowired annotation worked like a <constructor-arg> or <property>. @Autowired represents a place where a dependency is required.
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
                    if(fullyHydrated.isSuccess()){
                        view.displayPanel(fullyHydrated.getPayLoad());
                    } else {
                        view.displayError("[ERROR] Unable to add Panel.");
                    }

                    break;
                case DISPLAY_PANEL_BY_SECTION:
                    String section = view.readPanelSectionType();
                    List fully = service.findPanelBySection(section);
                    view.displayPanelsBySection(fully);
                    break;

                case EDIT_PANEL:
                    //1. get which panel we want to update
                    //1a. have user enter section name
                    String sectionName = view.readPanelSectionType();

                    //1b. grab all panels in that section from the service
                    List<Panel> sectionPanels = service.findPanelBySection( sectionName );


                    if( sectionPanels.isEmpty() ){
                        view.emptySection();
                        return;
                    }

                    //1c. have user select one panel from the list (by number)
                    Panel selectedPanel = view.selectSectionPanel( sectionName, sectionPanels );

                    //2. let the user update it
                    Panel updated = view.editPanel( selectedPanel );

                    //3. save the changes by passing it to the service.
                    PanelResult updateResult = service.updatePanel( updated );

                    //4. display the results
                    if( updateResult.isSuccess() ){
                        view.updateSuccess( updateResult.getPayload() );
                    } else {
                        view.printErrorMessage( updateResult.getMessages() );
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
                        view.displayError(result.getMessages().toString());
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
