import React, { useState } from 'react';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import FormControl from '@mui/material/FormControl';
import InputLabel from '@mui/material/InputLabel';
import Select from '@mui/material/Select';
import MenuItem from '@mui/material/MenuItem';

function App() {
  const [textInputs, setTextInputs] = useState(['']);
  const [selectInputs, setSelectInputs] = useState(['']);

  const handleTextInputChange = (e, index) => {
    const newInputs = [...textInputs];
    newInputs[index] = e.target.value;
    setTextInputs(newInputs);
  };

  const addTextInput = () => {
    setTextInputs([...textInputs, '']);
  };

  const handleSelectInputChange = (e, index) => {
    const newInputs = [...selectInputs];
    newInputs[index] = e.target.value;
    setSelectInputs(newInputs);
  };

  const addSelectInput = () => {
    setSelectInputs([...selectInputs, '']);
  };

  const handleSubmit = () => {
    const formData = {
      textInputs,
      selectInputs,
    };

    // Send a POST request to localhost:8080/process with formData
    fetch('http://localhost:8080/process', {
      method: 'POST',
      body: JSON.stringify(formData),
      headers: {
        'Content-Type': 'application/json',
      },
    })
        .then((response) => response.json())
        .then((data) => {
          console.log(data); // You can handle the response here
        })
        .catch((error) => {
          console.error('Error:', error);
        });
  };

  return (
      <div className="App">
        <form>
          <div>
            <TextField
                label="Text Input"
                variant="outlined"
                multiline
                rows={4}
            />
          </div>

          {textInputs.map((input, index) => (
              <div key={index}>
                <TextField
                    label="Additional Text Input"
                    variant="outlined"
                    value={input}
                    onChange={(e) => handleTextInputChange(e, index)}
                />
              </div>
          ))}

          <Button variant="contained" color="primary" onClick={addTextInput}>
            Add Text Input
          </Button>

          <div>
            <FormControl variant="outlined">
              <InputLabel>Select Input</InputLabel>
              <Select label="Select Input">
                {selectInputs.map((input, index) => (
                    <MenuItem key={index} value={input}>
                      {input}
                    </MenuItem>
                ))}
              </Select>
            </FormControl>
          </div>

          {selectInputs.map((input, index) => (
              <div key={index}>
                <FormControl variant="outlined">
                  <InputLabel>Additional Select Input</InputLabel>
                  <Select
                      label="Additional Select Input"
                      value={input}
                      onChange={(e) => handleSelectInputChange(e, index)}
                  >
                    {selectInputs.map((option, idx) => (
                        <MenuItem key={idx} value={option}>
                          {option}
                        </MenuItem>
                    ))}
                  </Select>
                </FormControl>
              </div>
          ))}

          <Button variant="contained" color="primary" onClick={addSelectInput}>
            Add Select Input
          </Button>

          <Button
              variant="contained"
              color="primary"
              onClick={handleSubmit}
              style={{ marginTop: '20px' }}
          >
            Submit
          </Button>
        </form>
      </div>
  );
}

export default App;
