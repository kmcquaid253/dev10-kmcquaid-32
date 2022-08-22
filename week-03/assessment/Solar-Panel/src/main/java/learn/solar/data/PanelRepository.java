package learn.solar.data;

import learn.solar.models.Panel;

import java.util.List;

public interface PanelRepository {
    List<Panel> findAll();

    Panel add(Panel toAdd) throws DataAccessException;

    boolean update(Panel editedPanel) throws DataAccessException;

    boolean deleteBySectionRowColumn(int id);

    List<Panel> findPanelBySection(String section) throws DataAccessException;

    Panel getPanelByLocation(String section, int row, int column);
}
