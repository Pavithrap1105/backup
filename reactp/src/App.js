import './App.css';

function App() {
  const name =<h1>Pavithra</h1>;
  const age = <h2>21</h2>
  const email=<h2>pavi@gmail.com</h2>
  return (
    <div className="App">
     <div>
      {name}
      {age}
      {email}
     </div>
    </div>
  );
}

export default App;
