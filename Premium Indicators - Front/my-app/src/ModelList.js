import React, { useState, useEffect } from 'react';

function ModelList() {
    const [models, setModels] = useState({});

    useEffect(() => {
        // Define the URL for the backend API
        const apiUrl = 'http://localhost:8080/allModels';

        // Fetch data from the backend
        fetch(apiUrl)
            .then((response) => response.json())
            .then((data) => {
                // Assuming the response is a Map<String, List<String>> object
                setModels(data);
            })
            .catch((error) => {
                console.error('Error fetching data:', error);
            });
    }, []);

    return (
        <div>
            <h1>List of Models we can use in our expressions</h1>
            <ul>
                {Object.entries(models).map(([key, value]) => (
                    <li key={key}>
                        <strong>{key}:</strong>
                        <ul>
                            {value.map((item, index) => (
                                <li key={index}>{item}</li>
                            ))}
                        </ul>
                    </li>
                ))}
            </ul>
        </div>
    );
}

export default ModelList;
