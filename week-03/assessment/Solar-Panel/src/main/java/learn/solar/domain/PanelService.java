package learn.solar.domain;

import learn.solar.data.DataAccessException;
import learn.solar.data.PanelFileRepository;
import learn.solar.data.PanelRepository;
import learn.solar.models.Panel;

import java.util.List;

public class PanelService {//talks to repo
    PanelRepository repository;

    public PanelService(PanelRepository repository){
        this.repository = repository;
    }

    public PanelResult addPanel(Panel partiallyHydrated) throws DataAccessException {
        //just pass through the inputs to our data layer for now, LATER i'll implement validation
        PanelResult result = new PanelResult();

        if(partiallyHydrated == null){
            result.addErrorMessage("Cannot add null Panel");
        }

        if(result.isSuccess()) {
            Panel fullyHydrated = repository.add(partiallyHydrated);
            result.setPayload(fullyHydrated);
        }
        return result;
    }

    public List<Panel> findPanelBySection(String section) throws DataAccessException{
        List<Panel> fullyhydrated = repository.findPanelBySection(section);
        return fullyhydrated;
    }

    public PanelResult getPanelByLocation(Panel panel) {

       PanelResult panelResult = new PanelResult();

       Panel panelLooking = repository.getPanelByLocation(
               panel.getSection(),
               panel.getRow(),
               panel.getColumn()
       );

       if(panelLooking == null){
           panelResult.addErrorMessage("Panel not found.");

       }else {
           panelResult.setPayload(panelLooking);

       }
       return panelResult;

    }

    public PanelResult updatePanel(Panel edited) throws DataAccessException {

        PanelResult result = new PanelResult();

        //you'll start this method off with validation checks (adding error messages to the result if you find problems)

        if( edited == null ){
            result.addErrorMessage( "Cannot add null panel.");
        } else {

            if(edited.getRow() <= 0 && edited.getRow() > 250){
                result.addErrorMessage("Row has to be between 1-250");
            }
            if(edited.getColumn() == 0 || edited.getColumn() > 250){
                result.addErrorMessage("Row has to be between 1-250");
            }
            if(edited.getYearInstalled() < 1884 || edited.getYearInstalled() >= 2022){
                result.addErrorMessage("Year has to be between 1884-2021");
            }
            if(edited.getPanelMaterial() == null){
                result.addErrorMessage("Panel Material cannot be null");
            }
        }
        if( result.isSuccess() ) {
            Panel fullyHydrated = repository.add(edited);
            result.setPayload(fullyHydrated);
        }

        //if there are no errors you'll send the edited panel to the repo to actually write out the changes to the file

        return result;
    }

}
