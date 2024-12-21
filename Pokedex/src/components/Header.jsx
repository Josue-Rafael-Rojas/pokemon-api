import { Link } from 'react-router-dom';

function Header() {
  return (
    <header className="header" style={{ marginBottom: '20px' }}>
      <nav>
        <ul>
          <li>
          <Link to="/abilities">Abilities</Link>
        </li>
          <li>
            <Link to="/pokemon">Pokémon</Link>
          </li>
          <li>
            <Link to="/entrenadores" className="btn btn-success">Entrenador</Link>
          </li>
¡
        </ul>
      </nav>
    </header>
  );
}

export default Header;
