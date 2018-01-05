package com.mirror2.common;

import com.mirror2.common.model.*;
import com.mirror2.common.service.GenericController;
import com.mirror2.security.model.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Constructor;
import java.util.Map;

/**
 * Created by Khomeni on 23-Feb-17.
 */
public class CommonController extends GenericController{

    @RequestMapping(value = "/createRegion.se")
    public ModelAndView createRegion(@RequestParam(required = false) String message) {
        Map<String, Object> map = getModelMap("Region");
        map.put("message", message);
        map.put("regionList", commonService.findAll(Region.class));
        return new ModelAndView("/common/region_create", map);
    }

    @RequestMapping(value = "/saveRegion.se")
    public ModelAndView saveRegion(@RequestParam String name,
                                   @RequestParam String description) {
        Map<String, Object> map = getModelMap("Country");
        Region region1 = new Region();
        region1.setName(name);
        region1.setDescription(description);
        commonService.save(region1);
        map.put("message", "Region Saved Successfully");
        map.put("regionList", commonService.findAll(Region.class));
        return new ModelAndView("/common/region_create", map);
    }


    @RequestMapping(value = "/createCountry.se")
    public ModelAndView createCountry(@RequestParam(required = false) String message) {
        Map<String, Object> map = getModelMap("Country");
        map.put("message", message);
        map.put("regionList", commonService.findAll(Region.class));
        return new ModelAndView("/common/country_create", map);
    }

    @RequestMapping(value = "/saveCountry.se")
    public ModelAndView saveCountry(@RequestParam String name,
                                    @RequestParam String description,
                                    @RequestParam Long regionId) {
        Map<String, Object> map = getModelMap("Country");
        Country country = new Country();
        country.setName(name);
        country.setDescription(description);
        Region region = commonService.get(Region.class, regionId);
        country.setRegion(region);
        commonService.save(country);
        map.put("message", "Country Saved Successfully");
        map.put("regionId", regionId);
//        map.put("countryList", commonService.findAll(Country.class, "region", region));
        return new ModelAndView("redirect:/common/countryList.se", map);
    }

    @RequestMapping(value = "/countryList.se")
    public ModelAndView countryList(@RequestParam(required = false) String message,
                                    @RequestParam(required = false) Long regionId) {
        Map<String, Object> map = getModelMap("Country");
        map.put("message", message);
        if (regionId != null) {
            Region region = commonService.get(Region.class, regionId);
            map.put("region", region);
            map.put("countryList", region.getCountryList());
        } else {
            map.put("countryList", commonService.findAll(Country.class));
        }
        return new ModelAndView("/common/country_list", map);
    }

    @RequestMapping(value = "/createDivision.se")
    public ModelAndView createDivision(@RequestParam(required = false) String message) {
        Map<String, Object> map = getModelMap("Division");
        map.put("message", message);
        map.put("countryList", commonService.findAll(Country.class));
        return new ModelAndView("/common/division_create", map);
    }

    @RequestMapping(value = "/saveDivision.se")
    public ModelAndView saveDivision(@RequestParam String name,
                                     @RequestParam String description,
                                     @RequestParam Long countryId) {
        Map<String, Object> map = getModelMap("Country");
        Division division = new Division();
        division.setName(name);
        division.setDescription(description);
        Country country = commonService.get(Country.class, countryId);
        division.setCountry(country);
        commonService.save(division);
        map.put("message", "Division Saved Successfully");
        map.put("countryId", countryId);
        // map.put("divisionList", commonService.findAll(Division.class, "country", country));
        return new ModelAndView("redirect:/common/divisionList.se", map);
    }


    @RequestMapping(value = "/divisionList.se")
    public ModelAndView divisionList(@RequestParam(required = false) String message,
                                     @RequestParam(required = false) Long countryId) {
        Map<String, Object> map = getModelMap("Country");
        map.put("message", message);
        if (countryId != null) {
            Country country = commonService.get(Country.class, countryId);
            map.put("country", country);
            map.put("divisionList", country.getDivisionList());
        } else {
            map.put("divisionList", commonService.findAll(Division.class));
        }
        return new ModelAndView("/common/division_list", map);
    }


    @RequestMapping(value = "/createDistrict.se")
    public ModelAndView createDistrict(@RequestParam(required = false) String message) {
        Map<String, Object> map = getModelMap("District");
        map.put("message", message);
        map.put("divisionList", commonService.findAll(Division.class));
        return new ModelAndView("/common/district_create", map);
    }

    @RequestMapping(value = "/saveDistrict.se")
    public ModelAndView saveDistrict(@RequestParam String name,
                                     @RequestParam String description,
                                     @RequestParam Long divisionId) {
        Map<String, Object> map = getModelMap("Country");
        District district = new District();
        district.setName(name);
        district.setDescription(description);
        Division division = commonService.get(Division.class, divisionId);
        district.setDivision(division);
        commonService.save(district);
        map.put("message", "District Saved Successfully");
        map.put("districtList", commonService.findAll(District.class, "division", division));

        return new ModelAndView("/common/district_list", map);
    }


    @RequestMapping(value = "/districtList.se")
    public ModelAndView districtList(@RequestParam(required = false) String message,
                                     @RequestParam(required = false) Long divisionId) {
        Map<String, Object> map = getModelMap("Country");
        map.put("message", message);
        if (divisionId != null) {
            Division division = commonService.get(Division.class, divisionId);
            map.put("division", division);
            map.put("districtList", division.getDistrictList());
        } else {
            map.put("districtList", commonService.findAll(District.class));
        }
        return new ModelAndView("/common/district_list", map);
    }

    @RequestMapping(value = "/createThana.se")
    public ModelAndView createThana(@RequestParam(required = false) String message) {
        Map<String, Object> map = getModelMap("Thana");
        map.put("message", message);
        map.put("districtList", commonService.findAll(District.class));
        return new ModelAndView("/common/thana_create", map);
    }

    @RequestMapping(value = "/saveThana.se")
    public ModelAndView saveThana(@RequestParam String name,
                                  @RequestParam String description,
                                  @RequestParam Long districtId) {
        Map<String, Object> map = getModelMap("Country");
        PoliceStation thana = new PoliceStation();
        thana.setName(name);
        thana.setDescription(description);
        District district = commonService.get(District.class, districtId);
        thana.setDistrict(district);
        commonService.save(thana);
        map.put("message", "Thana Saved Successfully");
        map.put("districtId", districtId);
        return new ModelAndView("redirect:/common/thanaList.se", map);
    }


    @RequestMapping(value = "/thanaList.se")
    public ModelAndView thanaList(@RequestParam(required = false) String message,
                                  @RequestParam(required = false) Long districtId) {
        Map<String, Object> map = getModelMap("Country");
        map.put("message", message);
        if (districtId != null) {
            District district = commonService.get(District.class, districtId);
            map.put("district", district);
            map.put("thanaList", district.getPoliceStationList());
        } else {
            map.put("thanaList", commonService.findAll(PoliceStation.class));
        }
        return new ModelAndView("/common/thana_list", map);
    }

    // Documents
    /*@RequestMapping(value = "/addSignature.se")
    public ModelAndView addSignature(@RequestParam Long id) {
        Map<String, Object> map = getModelMap("Add Signature");
        map.put("ownerObj", commonService.get(User.class, id));
        return new ModelAndView("/admin/add_signature", map);
    }

    @RequestMapping(value = "/saveSignature.se")
    public ModelAndView saveSignature(@RequestParam Long ownerId,
                                      @RequestParam MultipartFile signature) {

        User owner = commonService.get(User.class, ownerId);
        Document doc = owner.getSignature();
        doc = doc == null ? new Signature() : doc;
        doc.setTitle("Signature of " + owner.getName());
        doc.setOwner(owner);

        ActionResult result = commonService.writeDocument(doc, signature);
        result.put("id", owner.getId());
        return redirect(this, "viewEmployee", result);
    }*/


    /*@RequestMapping(value = "/addProfilePicture.se")
    public ModelAndView addProfilePicture(@RequestParam Long ownerId) {
        Map<String, Object> map = getModelMap("Add Profile Picture");
        map.put("ownerObj", commonService.get(User.class, ownerId));
        return new ModelAndView("/admin/add_avatar", map);
    }

    @RequestMapping(value = "/saveProfilePicture.se")
    public ModelAndView saveProfilePicture(@RequestParam Long ownerId, @RequestParam MultipartFile profilePic) {

        User owner = commonService.get(User.class, ownerId);
        Document doc = owner.getAvatar();
        doc = doc == null ? new Avatar() : doc;
        doc.setTitle("Profile Picture");
        doc.setOwner(owner);
        ActionResult result = commonService.writeDocument(doc, profilePic);
        result.put("id", owner.getId());

        return redirect(this, "viewEmployee", result);
    }*/

    //Address
    @RequestMapping(value = "/createAddress.se")
    public ModelAndView createAddress(@RequestParam(required = false) String type,
                                      @RequestParam Long holderId) {
        Map<String, Object> map = getModelMap("Address Create");
        map.put("type", type);
        map.put("holder", commonService.get(User.class, holderId));
        map.put("countryList", commonService.findAll(Country.class));
        return new ModelAndView("/common/address_create", map);
    }

    @RequestMapping(value = "/saveAddress.se", method = RequestMethod.POST)
    public ModelAndView saveAddress(@RequestParam Long holderId,
                                    @RequestParam String type,
                                    @RequestParam String line1,
                                    @RequestParam String line2,
                                    @RequestParam String po,
                                    @RequestParam String pc,
                                    @RequestParam Long countryId,
                                    @RequestParam(required = false) Long divisionId,
                                    @RequestParam(required = false) Long districtId,
                                    @RequestParam(required = false) Long policeStationId) throws Exception {
        User holder = commonService.get(User.class, holderId);
        Class<?> clazz = Class.forName((Address.class.getPackage() + "." + type + "Address").replace("package ", ""));
        Constructor<?> constructor = clazz.getConstructor();
        Address address = (Address) constructor.newInstance();
        address.setHolder(holder);
        address.setLine1(line1);
        address.setLine2(line2);
        address.setPo(po);
        address.setPc(pc);

        if (policeStationId != null) {
            PoliceStation ps = commonService.get(PoliceStation.class, policeStationId);
            address.setPs(ps);
        }
        if (districtId != null) {
            District d = commonService.get(District.class, districtId);
            address.setDistrict(d);
        }
        if (divisionId != null) {
            Division d = commonService.get(Division.class, divisionId);
            address.setDivision(d);
        }
        if (countryId != null) {
            Country c = commonService.get(Country.class, countryId);
            address.setCountry(c);
        }

        commonService.save(address);

        Map<String, Object> map = getModelMap("View Address");
        map.put("id", holderId);
        return new ModelAndView("redirect:/common/viewEmployee.se", map);
    }
}
