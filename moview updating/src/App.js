
import './App.css';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import { Header } from './components/Header';
import { Login } from './components/Login';
import { SignUp } from './components/Signup';
import { Footer } from './components/Footer';
import {BrowserRouter as Router,Routes,Route} from "react-router-dom";
import { Admin } from './components/Admin';
import { Home } from './components/Home';
import { Search } from './components/Search';
import { ViewReview} from './components/Admin Dashboard/Reviews/ViewReview';
import { ListReview } from './components/Admin Dashboard/Reviews/ListReview';
import { ListMovie } from './components/Admin Dashboard/Movie/ListMovie';
import { AddMovie } from './components/Admin Dashboard/Movie/AddMovie';
import { UpdateMovie } from './components/Admin Dashboard/Movie/UpdateMovie';
import { ListUser } from './components/Admin Dashboard/User/ListUser';
import { Categories } from './components/Categories';
import { Free } from './components/Free';
import { MoviePage } from './components/MoviePage';


function App() {
  return (
    <Router>
      <div className='Container'>
    <Header/>
    <Routes>
        <Route exact path="/" element ={<Home/>}/>
      </Routes>
      <Routes>
        <Route exact path="/home" element ={<Home/>}/>
      </Routes>
      {/* <Routes>
        <Route exact path="/logout" element ={<Home/>}/>
      </Routes> */}
      <Routes>
        <Route exact path="/login" element ={<Login/>}/>
      </Routes>
      <Routes>
        <Route exact path="/signup" element ={<SignUp/>}/>
      </Routes>
      <Routes>
        <Route exact path="/admin" element ={<Admin/>}/>
      </Routes>
      <Routes>
        <Route exact path="/search/:search" element ={<Search/>}/>
      </Routes>
      <Routes>
        <Route exact path="/reviews/:id" element ={<ViewReview/>}/>
      </Routes>
      <Routes>
        <Route exact path="/list/reviews" element ={<ListReview/>}/>
      </Routes>
      <Routes>
        <Route exact path="/movie/list" element ={<ListMovie/>}/>
      </Routes>
      <Routes>
        <Route exact path="/add/movie" element ={<AddMovie/>}/>
      </Routes>
      <Routes>
        <Route exact path="/update/movie/:id" element ={<UpdateMovie/>}/>
      </Routes>
      <Routes>
        <Route exact path="/users/list" element ={<ListUser/>}/>
      </Routes>
    <Footer/>
    <Routes>
        <Route exact path="/categories" element ={<Categories/>}/>
      </Routes>
      <Routes>
        <Route exact path="/free" element ={<Free/>}/>
      </Routes>
      <Routes>
        <Route exact path="/view/movie/:id" element ={<MoviePage/>}/>
      </Routes>
    </div>

    </Router>

  );
}

export default App;
