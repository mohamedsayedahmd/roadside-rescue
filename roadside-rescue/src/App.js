import "./App.css";
import TestComponent from "./Components/TestComponent";
import Login from "./Pages/Login/Login";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";

function App() {
  return (
    <>
      <Router>
        <Routes>
          <Route path="/" element={<Login />} />
          <Route path="/test" element={<TestComponent />} />
        </Routes>
      </Router>
    </>
  );
}

export default App;
// <div className="App">
//   <TestComponent />
//    <Login />
// </div>
