import React, { useState } from 'react';
import { Box, Button, TextField, Select, MenuItem } from '@mui/material';

const EntitySelector = ({ onAddBox, onInputChange }) => {
    const [selectValue, setSelectValue] = useState('');
    const [textValue, setTextValue] = useState('');

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
                <MenuItem value="option1">Option 1</MenuItem>
                <MenuItem value="option2">Option 2</MenuItem>
                {/* Add more options as needed */}
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