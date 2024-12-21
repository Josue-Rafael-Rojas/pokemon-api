import React, { useState } from 'react';
import { Form, Button, Alert } from 'react-bootstrap';

const GetCoach = () => {
  const [coachId, setCoachId] = useState('');
  const [coach, setCoach] = useState(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  const handleInputChange = (e) => {
    setCoachId(e.target.value);
  };

  const handleCoachChange = (e) => {
    const { name, value } = e.target;
    setCoach({ ...coach, [name]: value });
  };

  const fetchCoach = async () => {
    setLoading(true);
    setError(null);
    setCoach(null);

    try {
      const response = await fetch(`http://localhost:8080/coaches/${coachId}`);
      if (!response.ok) {
        const errorData = await response.json();
        setError(errorData.message);
      } else {
        const data = await response.json();
        console.log(data)
        setCoach(data);
      }
    } catch (error) {
      console.error('Error fetching coach:', error);
      setError('Error fetching coach');
    } finally {
      setLoading(false);
    }
  };

  const updateCoach = async () => {
    setLoading(true);
    setError(null);

    const body = {
      ...coach,
      score: parseFloat(coach.score),
      favoritePokemons: typeof coach.favoritePokemons === 'string' 
        ? coach.favoritePokemons.split(',').map(pokemon => pokemon.trim()) 
        : []
    };

    try {
      const response = await fetch(`http://localhost:8080/coaches/${coachId}`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(body)
      });

      if (!response.ok) {
        const errorData = await response.json();
        setError(errorData.message);
      } else {
        alert('Entrenador actualizado exitosamente');
      }
    } catch (error) {
      console.error('Error updating coach:', error);
      setError('Error updating coach');
    } finally {
      setLoading(false);
    }
  };

  const deleteCoach = async () => {
    setLoading(true);
    setError(null);

    try {
      const response = await fetch(`http://localhost:8080/coaches/${coachId}`, {
        method: 'DELETE'
      });

      if (!response.ok) {
        const errorData = await response.json();
        setError(errorData.message);
      } else {
        alert('Entrenador eliminado exitosamente');
        setCoach(null);
        setCoachId('');
      }
    } catch (error) {
      console.error('Error deleting coach:', error);
      setError('Error deleting coach');
    } finally {
      setLoading(false);
    }
  };

  return (
    <div style={{ margin: '20px' }}>
      <h2 style={{marginTop: "200"}}>Buscar Entrenador</h2>
      <Form>
        <Form.Group controlId="coachId">
          <Form.Label>ID del Entrenador</Form.Label>
          <Form.Control
            type="number"
            value={coachId}
            onChange={handleInputChange}
            placeholder="Ingrese el ID del entrenador"
          />
        </Form.Group>
        <Button variant="primary" onClick={fetchCoach}>
          Buscar
        </Button>
      </Form>
      {loading && <p>Loading...</p>}
      {error && <Alert variant="danger" style={{ marginTop: '20px' }}>{error}</Alert>}
      {coach && (
        <div style={{ marginTop: '20px' }}>
          <h2>Detalles del Entrenador</h2>
          <Form>
            <Form.Group controlId="name">
              <Form.Label>Nombre</Form.Label>
              <Form.Control
                type="text"
                name="name"
                value={coach.name}
                onChange={handleCoachChange}
              />
            </Form.Group>
            <Form.Group controlId="city">
              <Form.Label>Ciudad</Form.Label>
              <Form.Control
                type="text"
                name="city"
                value={coach.city}
                onChange={handleCoachChange}
              />
            </Form.Group>
            <Form.Group controlId="score">
              <Form.Label>Puntuación</Form.Label>
              <Form.Control
                type="text"
                name="score"
                value={coach.score}
                onChange={handleCoachChange}
              />
            </Form.Group>
            <Form.Group controlId="favoritePokemons">
              <Form.Label>Pokémones Favoritos</Form.Label>
              <Form.Control
                type="text"
                name="favoritePokemons"
                value={coach.favoritePokemons ? coach.favoritePokemons.join(', ') : ''}
                onChange={handleCoachChange}
                style={{ marginBottom: '20px' }}
              />
            </Form.Group>
            <Button variant="success" onClick={updateCoach} style={{ marginRight: '10px' }}>
              Actualizar
            </Button>
            <Button variant="danger" onClick={deleteCoach}>
              Eliminar
            </Button>
          </Form>
        </div>
      )}
    </div>
  );
};

export default GetCoach;