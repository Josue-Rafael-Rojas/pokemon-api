import React, { useState, useEffect } from 'react';
import { ListGroup } from 'react-bootstrap';

const AbilitiesPage = () => {
  const [abilities, setAbilities] = useState([]);

  useEffect(() => {
    const fetchAbilities = async () => {
      try {
        const response = await fetch('http://localhost:8080/pokemon/abilities');
        const data = await response.json();
        setAbilities(data);
      } catch (error) {
        console.error('Error fetching abilities:', error);
      }
    };

    fetchAbilities();
  }, []);

  return (
    <div style={{ margin: '20px' }}>
      <h1>Primeras 50 Habilidades de los Pokemones</h1>
      <ListGroup>
        {abilities.map((ability, index) => (
          <ListGroup.Item key={index}>{ability}</ListGroup.Item>
        ))}
      </ListGroup>
    </div>
  );
};

export default AbilitiesPage;