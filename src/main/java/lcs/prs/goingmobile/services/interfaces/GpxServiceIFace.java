package lcs.prs.goingmobile.services.interfaces;

import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import lcs.prs.goingmobile.entities.Journey;

public interface GpxServiceIFace {

	Set<Journey> processGpx(MultipartFile multipartFile, String name);

}