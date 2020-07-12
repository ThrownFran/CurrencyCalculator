package com.fab.currencycalculator.domain.schedulers;

import io.reactivex.Scheduler;

public interface SchedulersFacade {
    Scheduler io ();
    Scheduler computation ();
    Scheduler ui ();
}
