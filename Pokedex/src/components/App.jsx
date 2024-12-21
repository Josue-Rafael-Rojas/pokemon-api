import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import './App.css';
import Header from './components/Header';
import PokemonPage from './components/PokemonPage';
import TrainersPage from './components/TrainersPage';
import Welcome from './components/Welcome';
import 'bootstrap/dist/css/bootstrap.min.css'; 

function App() {
  return (
    <Router>
      <div className="app">
        <Header />
        <Routes>
          <Route path="/pokemon" element={<PokemonPage />} />
          <Route path="/pokemon" element={<AbilitiesPage />} />
          <Route path="/trainers" element={<TrainersPage />} />
          <Route path="/" element={<Welcome />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
