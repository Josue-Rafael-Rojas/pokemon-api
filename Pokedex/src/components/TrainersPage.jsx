import React, { useState } from 'react';
import { Form, Button, Alert, Modal } from 'react-bootstrap';
import GetCoach from './GetCoach';

const TrainersPage = () => {
  const [showModal, setShowModal] = useState(false);
  const handleShow = () => setShowModal(true);
  const handleClose = () => setShowModal(false);
  const [trainer, setTrainer] = useState({
    name: '',
    city: '',
    score: '',
    favoritePokemons: ''
  });
  const [error, setError] = useState(null);

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setTrainer({ ...trainer, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError(null);

    const body = {
      ...trainer,
      score: parseFloat(trainer.score),
      favoritePokemons: trainer.favoritePokemons.split(',').map(pokemon => pokemon.trim())
    };

   try {
      const response = await fetch('http://localhost:8080/coaches', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(body)
      });
    
      if (!response.ok) {
        const errorData = await response.json();
        setError(errorData.message);
      } else {
        const data = await response.json();
        alert(`Entrenador creado exitosamente con id ${data.id}`);
      }
    } catch (error) {
      console.error('Error creating trainer:', error);
      setError('Error creating trainer');
    }
  };

  return (
    <div style={{ margin: '20px' }}>
     <div style={{ display: 'flex', alignItems: 'center', justifyContent: 'space-between' }}>
        <h2>Entrenadores</h2>
        <Button variant="primary" onClick={handleShow}>
          Buscar Entrenador
        </Button>
      </div>
      <p>Este es el módulo de Entrenadores.</p>
      <Form onSubmit={handleSubmit}>
        <Form.Group controlId="name">
          <Form.Label>Nombre</Form.Label>
          <Form.Control
            type="text"
            name="name"
            value={trainer.name}
            onChange={handleInputChange}
            placeholder="Nombre del entrenador"
          />
        </Form.Group>
        <Form.Group controlId="city">
          <Form.Label>Ciudad</Form.Label>
          <Form.Control
            type="text"
            name="city"
            value={trainer.city}
            onChange={handleInputChange}
            placeholder="Ciudad del entrenador"
          />
        </Form.Group>
        <Form.Group controlId="score">
          <Form.Label>Puntuación</Form.Label>
          <Form.Control
            type="text"
            name="score"
            value={trainer.score}
            onChange={handleInputChange}
            placeholder="Puntuación del entrenador"
          />
        </Form.Group>
        <Form.Group controlId="favoritePokemons">
          <Form.Label>Pokémones Favoritos</Form.Label>
          <Form.Control
            type="text"
            name="favoritePokemons"
            value={trainer.favoritePokemons}
            onChange={handleInputChange}
            placeholder="IDs de Pokémones favoritos (separados por comas)"
          />
        </Form.Group>
        <Button variant="primary" type="submit" style={{ marginTop: '20px' }}>
          Crear Entrenador
        </Button>
      </Form>
      {error && <Alert variant="danger" style={{ marginTop: '20px' }}>{error}</Alert>}



      <Modal show={showModal} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>Buscar Entrenador</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <GetCoach />
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleClose}>
            Cerrar
          </Button>
        </Modal.Footer>
      </Modal>

    </div>
  );
};

export default TrainersPage;