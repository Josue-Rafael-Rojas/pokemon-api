import React, { useState } from 'react';
import { Form, Button, Card, ListGroup } from 'react-bootstrap';

const PokemonPage = () => {
  const [pokemonId, setPokemonId] = useState('');
  const [pokemonData, setPokemonData] = useState(null);

  const handleInputChange = (e) => {
    setPokemonId(e.target.value);
  };

  const fetchPokemonData = async () => {
    try {
      const response = await fetch(`http://localhost:8080/pokemon/${pokemonId}`);
      const data = await response.json();
      setPokemonData(data);
    } catch (error) {
      console.error('Error fetching Pokémon data:', error);
    }
  };

  return (
    <div style={{ margin: '20px' }}>
      <h2>Pokémon</h2>
      <p>Este es el módulo de Pokémon.</p>
      <Form>
        <Form.Group controlId="pokemonId">
          <Form.Label>Ingrese el ID del Pokémon</Form.Label>
          <Form.Control
            type="text"
            size="lg"
            style={{ width: '300px', border: '1px solid black', marginBottom: '10px' }}
            value={pokemonId}
            onChange={handleInputChange}
            placeholder="ID del Pokémon"
          />
        </Form.Group>
        <Button variant="primary" onClick={fetchPokemonData}>
          Buscar Pokémon
        </Button>
      </Form>
      {pokemonData && (
        <Card style={{ marginTop: '20px', border: '1px solid black' }}>
          <Card.Body>
            <Card.Title style={{ fontWeight: "700", textTransform: "capitalize", fontSize: "24px"  }}>{pokemonData.name}</Card.Title>
            <Card.Text>Altura: {pokemonData.height}</Card.Text>
            <Card.Text>Peso: {pokemonData.weight}</Card.Text>
            <Card.Title>Habilidades:</Card.Title>
            <ListGroup variant="flush">
              {pokemonData.abilities.map((ability, index) => (
                <ListGroup.Item key={index}>{ability.ability.name}</ListGroup.Item>
              ))}
            </ListGroup>
          </Card.Body>
        </Card>
      )}
    </div>
  );
}

export default PokemonPage;