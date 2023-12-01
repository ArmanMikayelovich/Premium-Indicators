import React, {useState} from 'react';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import {Box} from "@mui/material";
import ModelList from "./ModelList";
import ArgumentPaper from './argument/ArgumentPaper';
import EntitySelector from './entitySelector/EntitySelector';
import Divider from '@mui/material/Divider';


function App() {

    const [mainExpression, setMainExpression] = useState('');


    const [argumentPapers, setArgumentPapers] = useState([]); // State to store ArgumentPaper components
    const [argumentValues, setArgumentValues] = useState({}); // State to store argumentValues

    const [selectors, setSelectors] = useState([]);
    const [entitySelectorValues, setEntitySelectorValues] = useState([]);

    const handleMainExpressionChange = (event) => {
        setMainExpression(event.target.value);
    };

    const addArgumentPaper = () => {
        // Generate a unique key for each ArgumentPaper
        const key = `argument-${Date.now()}`;
        const newArgumentPaper = (<ArgumentPaper key={key} onValueChange={handleArgumentValueChange(key)}/>);

        // Update the argumentValues state with an empty object for the new ArgumentPaper
        setArgumentValues((prevValues) => ({
            ...prevValues, [key]: {},
        }));

        // Add the new ArgumentPaper to the state
        setArgumentPapers((prevArgumentPapers) => [...prevArgumentPapers, newArgumentPaper,]);
    };

    const handleArgumentValueChange = (key) => (field, value) => {
        // Update the argumentValues state with the argumentValues from each ArgumentPaper
        setArgumentValues((prevValues) => ({
            ...prevValues, [key]: {
                ...prevValues[key], [field]: value,
            },
        }));
    };


    const handleAddSelector = () => {
        setSelectors([...selectors, <EntitySelector key={selectors.length}
                                                    onAddSelector={handleAddSelector}
                                                    onInputChange={handleInputChange}
                                                    endpoint={'http://localhost:8080/allModels'}/>]);
    };

    const handleInputChange = (values) => {
        setEntitySelectorValues([...entitySelectorValues, values]);
    };

    const handleSubmit = () => {
        let formData = [mainExpression, argumentValues, entitySelectorValues];
        console.log('formData', formData);
        // Send a POST request to localhost:8080/process with formData
        // fetch('http://localhost:8080/process', {
        //     method: 'POST', body: JSON.stringify(formData), headers: {
        //         'Content-Type': 'application/json',
        //     },
        // })
        //     .then((response) => response.json())
        //     .then((data) => {
        //         console.log(data); // You can handle the response here
        //     })
        //     .catch((error) => {
        //         console.error('Error:', error);
        //     });
    };




    return (<div className="App">
        <form>
            <Box flexDirection={"row"} display={"column"}>
                <Box>
                    <Box m={2} flexDirection="column" display="flex">
                        <label>This is the main expression, which will be calculated via Math Framework</label>
                        <TextField
                            label="Text Input"
                            variant="outlined"
                            multiline
                            rows={4}
                            value={mainExpression}
                            onChange={handleMainExpressionChange}
                        />
                    </Box>
                </Box>

                <Box>
                    <label>There are additional inputs for arguments</label>
                    <Box>
                        <Button onClick={addArgumentPaper} variant="contained">
                            Add Argument
                        </Button>
                        {/* Render dynamically added ArgumentPaper components */}
                        <Box m={2} display="flex" flexWrap="wrap">
                            {argumentPapers.map((arg, index) => (
                                <Box key={index} m={2} width="25%"> {/* Set width to control max per row */}
                                    {arg}
                                </Box>
                            ))}
                        </Box>
                        {/* Display the argumentValues received from all ArgumentPaper components */}
                        <Box m={2}>{JSON.stringify(argumentValues, null, 2)}</Box>
                    </Box>
                </Box>

                <Divider light/>
                <Box>
                    <h6>Entity Selectors:</h6>
                    <Button onClick={handleAddSelector} variant="contained" color="primary">
                        Add Entity Selector
                    </Button>
                    {selectors.map((selector) => selector)}
                    <h6>Input Values:</h6>
                    <pre>{JSON.stringify(entitySelectorValues, null, 2)}</pre>
                </Box>

                <Box m={2}>
                    <Button
                        variant="contained"
                        color="primary"
                        onClick={handleSubmit}
                        style={{marginTop: '20px'}}>
                        Submit
                    </Button>
                </Box>
            </Box>
        </form>
        <Box m={2}>
            <ModelList/>
        </Box>

    </div>);
}

export default App;
