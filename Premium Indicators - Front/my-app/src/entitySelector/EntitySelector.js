import React, {useEffect, useState} from 'react';
import {Box, Button, MenuItem, Select, TextField} from '@mui/material';

const EntitySelector = ({onAddBox, onInputChange, endpoint}) => {
    const [selectValue, setSelectValue] = useState('');
    const [textValue, setTextValue] = useState('');
    const [options, setOptions] = useState([]);

    useEffect(() => {
        // Fetch options from the API when the component mounts
        fetch(endpoint)
            .then((response) => response.json())
            .then((data) => {
                setOptions(data);
            })
            .catch((error) => {
                console.error('Error fetching options:', error);
            });
    }, [endpoint]);

    const handleAddBox = () => {
        onAddBox();
    };

    const handleSelectChange = (event) => {
        setSelectValue(event.target.value);
        onInputChange({ selectValue: event.target.value, textValue });
    };

    const handleTextChange = (event) => {
        setTextValue(event.target.value);
        onInputChange({ selectValue, textValue: event.target.value });
    };

    return (
        <Box display="flex" alignItems="center">
            <Select value={selectValue} onChange={handleSelectChange}>
                {Object.keys(options).map((option) => (
                    <MenuItem key={option.key} value={option.value}>
                        {option.label}
                    </MenuItem>
                ))}
            </Select>
            <TextField
                value={textValue}
                onChange={handleTextChange}
                label="Text Input"
                variant="outlined"
                margin="dense"
            />
            <Button onClick={handleAddBox} variant="contained" color="primary">
                Add
            </Button>
        </Box>
    );
};

export default EntitySelector;