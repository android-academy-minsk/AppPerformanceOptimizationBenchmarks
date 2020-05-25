package com.androidacademy.minsk.appoptimization

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.profiling.R
import com.androidacademy.minsk.appoptimization.benchmark.core.Benchmark
import com.androidacademy.minsk.appoptimization.benchmark.core.BenchmarkSeparator
import com.androidacademy.minsk.appoptimization.benchmark.cpu.AnimateHiddenViewCpuBenchmarkTest
import com.androidacademy.minsk.appoptimization.benchmark.cpu.CpuBenchmarkTest
import com.androidacademy.minsk.appoptimization.benchmark.database.InsertEntitiesDatabaseBenchmark
import com.androidacademy.minsk.appoptimization.benchmark.database.SelectEntitiesDatabaseBenchmark
import com.androidacademy.minsk.appoptimization.benchmark.database.UpdateEntitiesDatabaseBenchmark
import com.androidacademy.minsk.appoptimization.benchmark.http.LoadBitmapHttpMemoryBenchmark
import com.androidacademy.minsk.appoptimization.benchmark.memory.*
import com.androidacademy.minsk.appoptimization.database.core.TestDatabase
import com.androidacademy.minsk.appoptimization.database.dao.EmployeeDao
import com.androidacademy.minsk.appoptimization.di.Components
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


class MainActivity : AppCompatActivity() {


    private val workerCoroutineScope = CoroutineScope(Dispatchers.IO)

    private val uiScope = CoroutineScope(Dispatchers.Main)

    @Inject
    lateinit var database: dagger.Lazy<TestDatabase>

    @Inject
    lateinit var employeeDao: EmployeeDao

    override fun onCreate(savedInstanceState: Bundle?) {
        Components.appComponent.inject(this)

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        createBenchmarkUI(findViewById(R.id.cgBenchmarks))
    }

    private fun createBenchmarkUI(benchmarksChipsGroup: ChipGroup) {

        val benchmarks: List<Benchmark> = createBenchmarks()


        val inflater = LayoutInflater.from(benchmarksChipsGroup.context)
        for (benchmark in benchmarks) {
            if (benchmark is BenchmarkSeparator) {
                val textView = inflater.inflate(
                    R.layout.benchmark_separator,
                    benchmarksChipsGroup,
                    false
                ) as TextView
                textView.setText(benchmark.name)
                benchmarksChipsGroup.addView(textView)
                continue
            }
            val chip =
                inflater.inflate(R.layout.benchmark_chip, benchmarksChipsGroup, false) as Chip
            chip.text = benchmark.name
            chip.tag = benchmark
            chip.setOnClickListener {
                workerCoroutineScope.launch {
                    (it.tag as Benchmark).execute()
                }
            }

            benchmarksChipsGroup.addView(chip)
        }
    }

    private fun createBenchmarks(): List<Benchmark> {
        val benchmarks: MutableList<Benchmark> = mutableListOf()

        val context = applicationContext
        benchmarks.add(BenchmarkSeparator("-------- CPU test --------", context, uiScope))
        benchmarks.add(AnimateHiddenViewCpuBenchmarkTest(findViewById(R.id.imageView), uiScope))

        benchmarks.add(CpuBenchmarkTest(context, uiScope))

        benchmarks.add(BenchmarkSeparator("-------- Memory test --------", context, uiScope))
        benchmarks.add(AllocateLargeObjectMemoryBenchmark(context, uiScope))
        benchmarks.add(AllocateSmallObjectsMemoryBenchmark(context, uiScope))
        benchmarks.add(BitmapsMemoryBenchmark(context, context.resources, uiScope))
        benchmarks.add(ExecutorsCoreThreadsMemoryBenchmark(context, uiScope))
        benchmarks.add(RunThreadsMemoryBenchmark(context, uiScope))

        benchmarks.add(BenchmarkSeparator("-------- HTTP test --------", context, uiScope))

        benchmarks.add(LoadBitmapHttpMemoryBenchmark(findViewById(R.id.imageView), uiScope))

        benchmarks.add(BenchmarkSeparator("-------- DB test --------", context, uiScope))
        benchmarks.add(InsertEntitiesDatabaseBenchmark(context, uiScope, database, employeeDao))
        benchmarks.add(UpdateEntitiesDatabaseBenchmark(context, uiScope, database, employeeDao))
        benchmarks.add(SelectEntitiesDatabaseBenchmark(context, uiScope, database, employeeDao))

        return benchmarks
    }

}