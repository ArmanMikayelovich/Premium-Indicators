// ApiSelect.js

import React, {useEffect, useState} from 'react';
import {MenuItem, Select} from '@mui/material';

const ApiSelect = ({endpoint, onChange}) => {
    const [options, setOptions] = useState([]);
    const [selectedValue, setSelectedValue] = useState('');

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

    const handleSelectChange = (event) => {
        const value = event.target.value;
        setSelectedValue(value);
        onChange(value); // Pass the selected value back to the parent component
    };

    return (
        <Select value={selectedValue} onChange={handleSelectChange}>
            {options.map((option) => (
                <MenuItem key={option.value} value={option.value}>
                    {option.label}
                </MenuItem>
            ))}
        </Select>
    );
};

export default ApiSelect;
