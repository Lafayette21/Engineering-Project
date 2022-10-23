package com.example.project.controller.parameters;

import com.example.project.Resource;
import com.example.project.database.model.ActorParameters;
import com.example.project.database.repository.ActorParametersRepository;
import com.example.project.database.repository.RepositoryManager;
import com.example.project.parametervalues.ActorsParametersValues;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.hibernate.service.spi.InjectService;

import java.net.URL;
import java.util.ResourceBundle;

public class ActorsParametersScreenController implements ParameterControlledScreen, Initializable {
    private static final int MIN_SPINNER_VALUE = 2;
    private static final int MAX_SPINNER_VALUE = 10;
    private static final int INITIAL_SPINNER_VALUE = 2;
    private static final int STEP_SPINNER_VALUE = 1;

    private ParametersScreenController screenParent;

    @FXML
    private TextField actorsNumberTextField;
    @FXML
    private Spinner<Integer> rowSpinner;
    @FXML
    private Spinner<Integer> columnSpinner;
    @FXML
    private Button updateButton;

    public void updateParameterValues() {
        ParametersValueHandler valueHandler = screenParent.getParametersValueHandler();
        int rowNumber = rowSpinner.getValue();
        int columnNumber = columnSpinner.getValue();

        ActorsParametersValues parametersValues = new ActorsParametersValues(rowNumber, columnNumber);
        valueHandler.updateValues(Resource.ActorParameters, parametersValues);

        updateActorsNumberTextField(rowNumber, columnNumber);
    }

    @Override
    public void setScreenParent(ParametersScreenController screenParent) {
        this.screenParent = screenParent;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        RepositoryManager repositoryManager = RepositoryManager.getInstance();
        ActorParametersRepository repository = (ActorParametersRepository) repositoryManager.getParameterRepositoryByResource(Resource.ActorParameters);

        setSpinnerValueFactory(rowSpinner);
        setSpinnerValueFactory(columnSpinner);

        ActorParameters initialActorParameters = new ActorParameters(rowSpinner.getValue(), columnSpinner.getValue());
        repository.registerActorParameters(initialActorParameters);

        rowSpinner.valueProperty().addListener(new RowSpinnerChangeListener(repository));
        columnSpinner.valueProperty().addListener(new ColumnSpinnerChangeListener(repository));
    }

    private void setSpinnerValueFactory(Spinner<Integer> spinner) {
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory
                .IntegerSpinnerValueFactory(MIN_SPINNER_VALUE, MAX_SPINNER_VALUE, INITIAL_SPINNER_VALUE, STEP_SPINNER_VALUE);

        spinner.setValueFactory(valueFactory);
    }

    private class RowSpinnerChangeListener implements ChangeListener<Integer> {
        private final ActorParametersRepository repository;

        public RowSpinnerChangeListener(ActorParametersRepository repository) {
            this.repository = repository;
        }

        @Override
        public void changed(ObservableValue<? extends Integer> observableValue, Integer oldValue, Integer newValue) {
            repository.updateNumberOfRow(newValue);
            setValueOnActorsNumberTextField(repository);
        }
    }

    private class ColumnSpinnerChangeListener implements ChangeListener<Integer> {
        private final ActorParametersRepository repository;

        public ColumnSpinnerChangeListener(ActorParametersRepository repository) {
            this.repository = repository;
        }

        @Override
        public void changed(ObservableValue<? extends Integer> observableValue, Integer oldValue, Integer newValue) {
            repository.updateNumberOfColumns(newValue);
            setValueOnActorsNumberTextField(repository);
        }
    }

    private void setValueOnActorsNumberTextField(ActorParametersRepository repository) {
        ActorParameters actorParameters = repository.getActorParameters();
        int numberOfRows = actorParameters.getNumberOfRows();
        int numberOfColumns = actorParameters.getNumberOfColumns();
        updateActorsNumberTextField(numberOfRows, numberOfColumns);
    }

    private void updateActorsNumberTextField(int rowNumber, int columnNumber) {
        int numberOfActors = rowNumber * columnNumber;

        actorsNumberTextField.setText(String.valueOf(numberOfActors));
    }
}


