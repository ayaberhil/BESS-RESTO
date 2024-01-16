import http from "../http-common";


    
    const getAll = () => {
        return http.get("/projet/series");
    }

    const SerieService = {
        getAll
    }
    

export default SerieService;