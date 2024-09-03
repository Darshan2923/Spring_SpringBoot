package com.freecodecampspring.freecodecampspring.school;

import java.util.List;

public record SchoolDto(
                String name,
                List<Integer> studentIds) {

}
