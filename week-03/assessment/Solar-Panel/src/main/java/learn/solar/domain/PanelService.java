package learn.solar.domain;

import learn.solar.data.DataAccessException;
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
            return result;
        }

        Panel existingPanel = repository.getPanelByLocation(partiallyHydrated.getSection(), partiallyHydrated.getRow(), partiallyHydrated.getColumn());
        if(existingPanel != null){
            result.addErrorMessage("Cannot add duplicate panels");
            return result;
        }

        if(partiallyHydrated.getRow() < 0 || partiallyHydrated.getRow() > 250){
            result.addErrorMessage("Row has to be between 1 - 250");
            return result;
        }

        if(partiallyHydrated.getColumn() < 0 || partiallyHydrated.getColumn() > 250){
            result.addErrorMessage("Column has to be between 1 - 250");
            return result;
        }

        if (partiallyHydrated.getYearInstalled() < 1884 || partiallyHydrated.getYearInstalled() > 2021){
            result.addErrorMessage("Year Installed has to be between 1884 - 2021");
            return result;
        }

        if (partiallyHydrated.getPanelMaterial() == null){
            result.addErrorMessage("Panel Material cannot be null");
            return result;
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


        if (edited == null) {
            result.addErrorMessage("Panel is required");
        }

        if (result.isSuccess()) {
            if (repository.update(edited)) {
                result.setPayload(edited);
            } else {
                String message = String.format("Panel not found");
                result.addErrorMessage(message);
            }
        }
        return result;
    }


    public PanelResult deleteBySectionRowColumn(String lookupSection, int lookupRow, int lookupColumn) throws DataAccessException {
        PanelResult result = new PanelResult();

        if (!repository.deleteBySectionRowColumn(lookupSection, lookupRow, lookupColumn)) {
            String message = String.format("Panel %s-%s-%s not found", lookupSection, lookupRow, lookupColumn);
            result.addErrorMessage(message);
        }
        return result;
    }
}
