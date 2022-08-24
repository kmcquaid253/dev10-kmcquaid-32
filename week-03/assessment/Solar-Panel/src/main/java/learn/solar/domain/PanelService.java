package learn.solar.domain;

import learn.solar.data.DataAccessException;
import learn.solar.data.PanelRepository;
import learn.solar.models.Panel;

import java.time.LocalDate;
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
        return repository.findPanelBySection( section );
    }


    public PanelResult updatePanel(Panel updated) throws DataAccessException {
        PanelResult result = validate( updated );

        if( !result.isSuccess() ) return result;

        Panel atDestinationLocation = null;
        List<Panel> sectionPanels = repository.findPanelBySection(updated.getSection() );

        for( Panel toCheck : sectionPanels ){
            if( toCheck.getRow() == updated.getRow() && toCheck.getColumn() == updated.getColumn()){
                atDestinationLocation = toCheck;
                break;
            }
        }

        if( atDestinationLocation != null && atDestinationLocation.getPanelId() != updated.getPanelId()){
            //we're trying to move a Panel on top of a different panel
            //if the ids match, that just means we found the old record

            result.addMessage("Cannot place Panel on occupied location.");

        }

        if( result.isSuccess() ){
            boolean success = repository.update( updated );
            if( success ){
                result.setPayload( updated );
            } else {
                result.addMessage( "Could not find matching Panel.");
            }
        }

        return result;
    }

    private PanelResult validate(Panel toValidate) {
        PanelResult result = new PanelResult();

        if( toValidate == null ){
            result.addMessage("Cannot have null Panel.");
            return result;
        }

        if( toValidate.getColumn() < 1 || toValidate.getColumn() > 250 ){
            result.addMessage("Panel column must be between 1 and 250.");
        }

        if( toValidate.getRow() < 1 || toValidate.getRow() > 250 ){
            result.addMessage("Panel row must be between 1 and 250.");
        }

        if( toValidate.getPanelMaterial() == null ){
            result.addMessage("Cannot have Panel with missing Material.");
        }

        if( toValidate.getSection() == null || toValidate.getSection().isBlank() ){
            result.addMessage("Cannot have Panel with missing section.");
        }

        if( toValidate.getYearInstalled() > LocalDate.now().getYear()){
            result.addMessage("Cannot have Panel with future year.");
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
