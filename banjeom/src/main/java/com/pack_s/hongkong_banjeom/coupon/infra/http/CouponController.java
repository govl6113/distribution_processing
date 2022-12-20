package com.pack_s.hongkong_banjeom.coupon.infra.http;

import com.pack_s.hongkong_banjeom.coupon.application.CouponServiceImpl;
import com.pack_s.hongkong_banjeom.coupon.domain.Coupon;
import com.pack_s.hongkong_banjeom.coupon.infra.http.request.CouponCreateRequest;
import com.pack_s.hongkong_banjeom.coupon.infra.http.request.CouponUpdateRequest;
import com.pack_s.hongkong_banjeom.coupon.infra.http.response.CouponResponse;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/coupon")
@RequiredArgsConstructor
public class CouponController {

    private final CouponServiceImpl couponService;

    @PostMapping("/new")
    public ResponseEntity<CouponResponse> create(
        @Valid @RequestBody CouponCreateRequest request
    ) {
        return ResponseEntity.ok().body(couponService.create(request).toResponse());
    }

    @PutMapping("/{couponId}")
    public ResponseEntity<CouponResponse> update(
        @PathVariable("couponId") Long couponId,
        @Valid @RequestBody CouponUpdateRequest request
    ) {
        return ResponseEntity.ok().body(couponService.update(couponId, request).toResponse());
    }

    @PutMapping("/addSeal/{couponId}")
    public ResponseEntity<CouponResponse> addSeal(
        @PathVariable("couponId") Long couponId
    ) {
        return ResponseEntity.ok().body(couponService.addSeal(couponId).toResponse());
    }

    @GetMapping("/{couponId}")
    public ResponseEntity<CouponResponse> get(
        @PathVariable("couponId") Long couponId
    ) {
        return ResponseEntity.ok().body(couponService.get(couponId).toResponse());
    }

    @GetMapping("list")
    public ResponseEntity<List<CouponResponse>> getList() {
        return ResponseEntity.ok().body(
            couponService.getList()
                .stream().map(Coupon::toResponse)
                .collect(Collectors.toList())
        );
    }

    @DeleteMapping("/{couponId}")
    public ResponseEntity<Boolean> delete(
        @PathVariable("couponId") Long couponId
    ) {
        return ResponseEntity.ok().body(couponService.delete(couponId));
    }
}
