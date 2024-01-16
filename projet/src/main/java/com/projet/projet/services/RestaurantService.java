package com.projet.projet.services;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.projet.projet.beans.Etat;
import com.projet.projet.beans.Photo;
import com.projet.projet.beans.Restaurant;
import com.projet.projet.beans.User;
import com.projet.projet.beans.Ville;
import com.projet.projet.beans.Zone;
import com.projet.projet.enums.EtatEnum;
import com.projet.projet.repositories.EtatRepository;
import com.projet.projet.repositories.PhotoRepository;
import com.projet.projet.repositories.RestaurantRepository;
import com.projet.projet.repositories.SerieRepository;
import com.projet.projet.repositories.SpecialiteRepository;
import com.projet.projet.repositories.ZoneRepository;

import net.coobird.thumbnailator.Thumbnails;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private EtatRepository etatRepository;

    @Autowired
    private SerieRepository serieRepository;

    @Autowired
    private SpecialiteRepository specialiteRepository;

    @Autowired
    private PhotoRepository photoRepository;

     @Autowired
    private ZoneRepository zoneRepository;

    public List<Restaurant> getAll() {
        return restaurantRepository.findAll();
    }

    public List<Restaurant> getAllByUser(User user) {
        return restaurantRepository.findAllByProprietaire(user);
    }

    public List<Restaurant> getAllByZone(Zone zone) {
        return restaurantRepository.findAllByZone(zone.getId());
    }

    public List<Restaurant> getAllBySerie(Long serie) {
        return restaurantRepository.findAllBySerie(serieRepository.getById(serie));
    }

    public List<Restaurant> getAllByVille(Ville ville) {
        return restaurantRepository.findAllByVille(ville.getId());
    }

    public List<Restaurant> getAllBySpecialite(Long spec) {
		System.out.println("spec");
		return restaurantRepository.findAllBySpeciality(specialiteRepository.getById(spec));
	}

    public List<Restaurant> getAllByVilleAndZone(Ville ville, Zone zone) {
        return restaurantRepository.findAllByVilleAndZone(ville.getId(), zone.getId());
    }


    public Restaurant addRestaurant(MultipartFile[] files, String nom, String adresse, Long zone, Long serie,
            double lati, double longi, String ouverture, String fermeture, Long speciality, User user) {

        Etat etat = etatRepository.findByNom(EtatEnum.EN_COURS.name());
        Restaurant r = new Restaurant();

        r.setNom(nom);
        r.setAdresse(adresse);
        r.setHourcl(fermeture);
        r.setHourop(ouverture);
        r.setLati(lati);
        r.setLongi(longi);
        r.setProprietaire(user);
        r.setEtat(etat);
        r.setRank(3);
        r.setSerie(serieRepository.getById(serie));
        r.setZone(zoneRepository.getById(zone));
        r.setSpeciality(specialiteRepository.getById(speciality));

        restaurantRepository.save(r);
        r = addPhotosToRestau(r, files);
        return restaurantRepository.save(r);
    }

    public Restaurant addImages(MultipartFile[] files, Long id) {
        Restaurant restaurant = restaurantRepository.getById(id);
        addPhotosToRestau(restaurant, files);
        return restaurantRepository.save(restaurant);
    }

    public long count() {
        return restaurantRepository.count();
    }

    public void delete(Long id) {
        restaurantRepository.deleteById(id);
    }

    private Restaurant addPhotosToRestau(Restaurant restaurant, MultipartFile[] photos) {

        String fileName = new String();
        Set<Photo> pics = new HashSet<Photo>();

        if (restaurant.getId() == null) {
            restaurantRepository.save(restaurant);
        }

        for (MultipartFile pic : photos) {
            fileName = StringUtils.cleanPath(pic.getOriginalFilename());
            if (fileName.contains("..")) {
                System.out.println("not a valid file");
            }
            try {
                Photo photo = new Photo();
                photo.setImage(resizeImageForUse(Base64.getEncoder().encodeToString(pic.getBytes()), 400, 400));
                photo.setRestaurant(restaurant);
                photoRepository.save(photo);
                pics.add(photo);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        restaurant.setPhotos(pics);
        return restaurant;
    }

    public String resizeImageForUse(String src, int width, int height) {
        BufferedImage image = base64ToBufferedImage(src);
        try {
            image = resizeImage(image, width, height);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            return bufferedImageTobase64(image);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private BufferedImage resizeImage(BufferedImage image, int width, int height) throws IOException {
        ByteArrayOutputStream outputstream = new ByteArrayOutputStream();
        try {
            Thumbnails.of(image).size(width, height).outputFormat("JPEG").outputQuality(1).toOutputStream(outputstream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] data = outputstream.toByteArray();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
        return ImageIO.read(inputStream);
    }

    private BufferedImage base64ToBufferedImage(String base64Img) {
        BufferedImage image = null;
        byte[] decodedBytes = Base64.getDecoder().decode(base64Img);

        try {
            image = ImageIO.read(new ByteArrayInputStream(decodedBytes));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    private String bufferedImageTobase64(BufferedImage image) throws UnsupportedEncodingException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "JPEG", Base64.getEncoder().wrap(out));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString(StandardCharsets.ISO_8859_1.name());
    }
}
