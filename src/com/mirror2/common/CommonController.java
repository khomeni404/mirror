package com.mirror2.common;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mirror2.common.model.*;
import com.mirror2.common.service.GenericController;
import com.mirror2.csd.model.Customer;
import com.mirror2.security.model.User;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Type;
import java.util.*;

/**
 * Created by Khomeni on 23-Feb-17.
 */
@Controller("/common/")
public class CommonController extends GenericController{

    @RequestMapping(method = RequestMethod.GET, value = "/createRegion.erp")
    public ModelAndView createRegion(@RequestParam(required = false) String message) {
        Map<String, Object> map = getModelMap("Region");
        map.put("message", message);
        map.put("regionList", commonService.findAll(Region.class));
        return new ModelAndView("/common/region_create", map);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/saveRegion.erp")
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


    @RequestMapping(method = RequestMethod.GET, value = "/createCountry.erp")
    public ModelAndView createCountry(@RequestParam(required = false) String message) {
        Map<String, Object> map = getModelMap("Country");
        map.put("message", message);
        map.put("regionList", commonService.findAll(Region.class));
        return new ModelAndView("/common/country_create", map);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/saveCountry.erp")
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
        return new ModelAndView("redirect:/common/countryList.erp", map);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/countryList.erp")
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

    @RequestMapping(method = RequestMethod.GET, value = "/createDivision.erp")
    public ModelAndView createDivision(@RequestParam(required = false) String message) {
        Map<String, Object> map = getModelMap("Division");
        map.put("message", message);
        map.put("countryList", commonService.findAll(Country.class));
        return new ModelAndView("/common/division_create", map);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/saveDivision.erp")
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
        return new ModelAndView("redirect:/common/divisionList.erp", map);
    }


    @RequestMapping(method = RequestMethod.GET, value = "/divisionList.erp")
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


    @RequestMapping(method = RequestMethod.GET, value = "/createDistrict.erp")
    public ModelAndView createDistrict(@RequestParam(required = false) String message) {
        Map<String, Object> map = getModelMap("District");
        map.put("message", message);
        map.put("divisionList", commonService.findAll(Division.class));
        return new ModelAndView("/common/district_create", map);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/saveDistrict.erp")
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


    @RequestMapping(method = RequestMethod.GET, value = "/districtList.erp")
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

    @RequestMapping(method = RequestMethod.GET, value = "/createThana.erp")
    public ModelAndView createThana(@RequestParam(required = false) String message) {
        Map<String, Object> map = getModelMap("Thana");
        map.put("message", message);
        map.put("districtList", commonService.findAll(District.class));
        return new ModelAndView("/common/thana_create", map);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/saveThana.erp")
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
        return new ModelAndView("redirect:/common/thanaList.erp", map);
    }


    @RequestMapping(method = RequestMethod.GET, value = "/thanaList.erp")
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
    /*@RequestMapping(value = "/addSignature.erp")
    public ModelAndView addSignature(@RequestParam Long id) {
        Map<String, Object> map = getModelMap("Add Signature");
        map.put("ownerObj", commonService.get(User.class, id));
        return new ModelAndView("/admin/add_signature", map);
    }

    @RequestMapping(value = "/saveSignature.erp")
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


    /*@RequestMapping(value = "/addProfilePicture.erp")
    public ModelAndView addProfilePicture(@RequestParam Long ownerId) {
        Map<String, Object> map = getModelMap("Add Profile Picture");
        map.put("ownerObj", commonService.get(User.class, ownerId));
        return new ModelAndView("/admin/add_avatar", map);
    }

    @RequestMapping(value = "/saveProfilePicture.erp")
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
    @RequestMapping(value = "/createAddress.erp")
    public ModelAndView createAddress(@RequestParam(required = false) String type,
                                      @RequestParam Long holderId) {
        Map<String, Object> map = getModelMap("Address Create");
        map.put("type", type);
        map.put("holder", commonService.get(User.class, holderId));
        map.put("countryList", commonService.findAll(Country.class));
        return new ModelAndView("/common/address_create", map);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/saveAddress.erp")
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
        return new ModelAndView("redirect:/common/viewEmployee.erp", map);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/createBadge.erp")
    public ModelAndView createBadge(@RequestParam(required = false) String message) {
        Map<String, Object> map = getModelMap("Badge");
        map.put("message", message);
        map.put("badgeList", commonService.findAll(Badge.class));
        return new ModelAndView("/common/badge_create", map);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/saveBadge.erp")
    public ModelAndView saveBadge(@RequestParam(required = false) String message,
                                  @RequestParam String name,
                                  @RequestParam String badgeSign,
                                  @RequestParam String description) {
        Map<String, Object> map = getModelMap("Badge", message);
        Badge badge = new Badge();
        badge.setName(name);
        badge.setBadgeSign(badgeSign);
        badge.setDescription(description);
        commonService.save(badge);
        map.put("message", "Badge saved Successfully");
        map.put("badgeList", commonService.findAll(Badge.class));
        return new ModelAndView("redirect:/common/badgeList.erp", map);
    }


    @RequestMapping(method = RequestMethod.GET, value = "/viewBadge.erp")
    public ModelAndView viewBadge(@RequestParam(required = false) String message,
                                  @RequestParam Long id) {
        Map<String, Object> map = getModelMap("Badge");
        map.put("message", message);
        map.put("badge", commonService.get(Badge.class, id));
        return new ModelAndView("/common/badge_view", map);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/badgeList.erp")
    public ModelAndView badgeList(@RequestParam(required = false) String message) {
        Map<String, Object> map = getModelMap("Badge");
        map.put("message", message);
        map.put("badgeList", commonService.findAll(Badge.class));
        return new ModelAndView("/common/badge_list", map);
    }



    @RequestMapping(method = RequestMethod.GET, value = "/addCustomerBadge.erp")
    public ModelAndView addCustomerBadge(@RequestParam Long id,
                                         Model model,
                                         @RequestParam(required = false) String message) {
        model.addAllAttributes(getModelMap("Customer Badge", message));
        Customer customer = commonService.get(Customer.class, id);
        //List<Badge> badgeList = commonService.findAll(Badge.class);
        //List<Badge> memberBadges = commonService.findAll(Badge.class, "customerList", "id", id); // member.getBadgeList();
        /*if (!CollectionUtils.isEmpty(memberBadges))
            badgeList.removeAll(memberBadges);
        model.addAttribute("badgeList", badgeList);
        model.addAttribute("customerBadgeList", memberBadges);*/
        model.addAttribute("message", message);

        List<Map<String, String>> dataList = adminService.getCustomerBadgeDataMapList(id);
        model.addAttribute("dataList", dataList);


        if (!model.containsAttribute("Customer")) {
            model.addAttribute("Customer", customer);
        }
        return new ModelAndView("/csd/add_badge");
    }

    @RequestMapping(method = RequestMethod.POST, value = "/updateCustomerBadge.erp")
    public ModelAndView updateCustomerBadge(@RequestParam(required = false) String message,
                                            @RequestParam Long customerId,
                                            @RequestParam String jsonArray) throws IOException {
        Map<String, Object> map = getModelMap("Group of User", message);
        Gson gson = new Gson();
        Type type = new TypeToken<List<Badge>>() {
        }.getType();
        List<Badge> groupList = gson.fromJson("[" + jsonArray + "]", type);
        Customer customer = commonService.get(Customer.class, customerId);
        customer.setBadgeList(groupList);
        commonService.update(customer);
        map.put("message", "Badge been assigned successfully.");

        return new ModelAndView("redirect:/csd/viewCustomer.erp?id="+customerId);
        //return new ModelAndView("/csd/add_badge", map);
    }


}
