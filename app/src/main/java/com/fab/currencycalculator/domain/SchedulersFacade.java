package com.fab.currencycalculator.domain;

import io.reactivex.Scheduler;

public interface SchedulersFacade {
    Scheduler io ();
    Scheduler computation ();
    Scheduler ui ();
}
