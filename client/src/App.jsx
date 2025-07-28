import './App.css'
import {Route, Routes} from "react-router-dom";
import HomePage from "./pages/HomePage.jsx";
import TNavbar from "./components/Navbar.jsx";

function App() {

  return (
    <>
        <TNavbar />
        <Routes>
            <Route path='/' element={<HomePage />} />
        </Routes>
    </>
  )
}

export default App
