import React, { useState } from 'react';
import Paper from '@mui/material/Paper';
import TextField from '@mui/material/TextField';
import Typography from '@mui/material/Typography';

const ArgumentPaper = ({ onValueChange }) => {
    const [name, setName] = useState('');
    const [expression, setExpression] = useState('');

    const handleNameChange = (e) => {
        setName(e.target.value);
        onValueChange('name', e.target.value);
    };

    const handleExpressionChange = (e) => {
        setExpression(e.target.value);
        onValueChange('expression', e.target.value);
    };

    return (
        <div>
            <Paper elevation={3} style={{ padding: '20px', marginBottom: '20px' }}>
                <Typography variant="h6">Argument: {name}</Typography>
                <TextField
                    label="Name"
                    fullWidth
                    value={name}
                    onChange={handleNameChange}
                    variant="outlined"
                    style={{ marginTop: '10px' }}
                />
                <TextField
                    label="Expression"
                    fullWidth
                    value={expression}
                    onChange={handleExpressionChange}
                    variant="outlined"
                    style={{ marginTop: '10px' }}
                />
            </Paper>
        </div>
    );
};

export default ArgumentPaper;
