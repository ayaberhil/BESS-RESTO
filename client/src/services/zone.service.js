import http from "../http-common";

    const getAllByVille = id => {
        return http.get(`/projet/zone/${id}`);
    }
    
    const ZoneService = {
        getAllByVille,
    }
    

export default ZoneService;