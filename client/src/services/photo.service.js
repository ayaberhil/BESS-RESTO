// PhotoService.js

class PhotoService {

    static apiUrl = 'your-api-endpoint';

    static async getPhotos() {
        try {
            const response = await fetch(`${this.apiUrl}/photos`);
            const data = await response.json();
            return data;
        } catch (error) {
            console.error('Error fetching photos:', error);
            throw error;
        }
    }

    static async uploadPhoto(photoData) {
        try {
            const response = await fetch(`${this.apiUrl}/photos/upload`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(photoData),
            });
            const data = await response.json();
            return data;
        } catch (error) {
            console.error('Error uploading photo:', error);
            throw error;
        }
    }
}

export default PhotoService;
